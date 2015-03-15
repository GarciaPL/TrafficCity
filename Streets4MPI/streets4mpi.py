#!/usr/bin/env python
# -*- coding: utf-8 -*-
#
# streets4mpi.py
# Copyright 2012 Julian Fietkau <http://www.julian-fietkau.de/>,
#                Joachim Nitschke
#
# This file is part of Streets4MPI.
#
# Streets4MPI is free software: you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation, either version 3 of the License, or
# (at your option) any later version.
#
# Streets4MPI is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
#
# You should have received a copy of the GNU General Public License
# along with Streets4MPI.  If not, see <http://www.gnu.org/licenses/>.
#

from datetime import datetime
from random import random
from random import seed
from array import array
from itertools import repeat

from mpi4py import MPI

from osmdata import GraphBuilder
from tripgenerator import TripGenerator
from simulation import Simulation
from settings import settings
from persistence import persist_write
from utils import merge_arrays

import pickle
import sys
import struct
import argparse

import sys

parser = argparse.ArgumentParser(description='TrafficCity')
parser.add_argument('--home-dir', '--hd', dest="home_dir", nargs=1, required = True, help='Home directory')
parser.add_argument('--project', '--p', dest="project_name", nargs=1, required = True, help='Project name')
parser.add_argument('--osm', '--o', dest="osm_name", nargs=1, required = True, help='OSM file name')

# This class runs the Streets4MPI program.
class Streets4MPI(object):

    def __init__(self):
    	args = parser.parse_args()

	self.home_dir = args.home_dir[0]
	self.home_out = self.home_dir + "osm/" + args.project_name[0] + "/"
	self.project_name = args.project_name[0]
	self.osm_name = self.home_dir + "osm/" + args.project_name[0] + "/" + args.osm_name[0] + ".osm" #"/home/lciesluk/Pulpit/Streets4MPI/Streets4MPI-master/osm/projectName/osmName.osm"

        communicator = MPI.COMM_WORLD
        self.process_rank = communicator.Get_rank()
        number_of_processes = communicator.Get_size()

        # set random seed based on process rank
        random_seed = settings["random_seed"] + (37 * self.process_rank)
        seed(random_seed)

        self.log("Reading OpenStreetMap data...")
	self.log(self.osm_name)

        data = GraphBuilder(self.osm_name) 

        self.log("Building street network...")
        street_network = data.build_street_network() #funkcja z pliku osmdata

        if self.process_rank == 0 and settings["persist_traffic_load"]:
            self.log_indent("Saving street network to disk...")
            persist_write(self.home_out + "street_network_1.s4mpi", street_network)

        self.log("Locating area types...")
        data.find_node_categories()

        self.log("Generating trips...")
        trip_generator = TripGenerator()

        # distribute residents over processes
        number_of_residents = settings["number_of_residents"] / number_of_processes

        if settings["use_residential_origins"]:
            potential_origins = data.connected_residential_nodes
        else:
            potential_origins = street_network.get_nodes()

        potential_goals = data.connected_commercial_nodes | data.connected_industrial_nodes
        trips = trip_generator.generate_trips(number_of_residents, potential_origins, potential_goals)

        # set traffic jam tolerance for this process and its trips
        jam_tolerance = random()	
        self.log("Setting traffic jam tolerance to", str(round(jam_tolerance, 2)))

        # run simulation
        simulation = Simulation(street_network, trips, jam_tolerance, self.log_indent)

        for step in range(settings["max_simulation_steps"]):

            if step > 0 and step % settings["steps_between_street_construction"] == 0:
                self.log_indent("Road construction taking place...")
                simulation.road_construction()
                if self.process_rank == 0 and settings["persist_traffic_load"]:
                    persist_write("street_network_" + str(step + 1) + ".s4mpi", simulation.street_network)

            self.log("Running simulation step", step + 1, "of", str(settings["max_simulation_steps"]) + "...")
            simulation.step()

            # gather local traffic loads from all other processes
            self.log("Exchanging traffic load data between nodes...")
            total_traffic_load = array("I", repeat(0, len(simulation.traffic_load)))
            communicator.Allreduce(simulation.traffic_load, total_traffic_load, MPI.SUM)
            simulation.traffic_load = total_traffic_load
            simulation.cumulative_traffic_load = merge_arrays((total_traffic_load, simulation.cumulative_traffic_load))

            if self.process_rank == 0 and settings["persist_traffic_load"]:
                self.log_indent("Saving traffic load to disk...")
                persist_write(self.home_out + "traffic_load_" + str(step + 1) + ".s4mpi", total_traffic_load, is_array = True)

            del total_traffic_load

        self.log("Done!")

    def log(self, *output):
        if(settings["logging"] == "stdout"):
            print "[ %s ][ p%d ]" % (datetime.now(), self.process_rank),
            for o in output:
                print o,
            print ""

    def log_indent(self, *output):
        if(settings["logging"] == "stdout"):
            print "[ %s ][ p%d ]  " % (datetime.now(), self.process_rank),
            for o in output:
                print o,
            print ""

    def __call__(self, parser, namespace, values, option_string=None):
        print
        print 'Processing CustomAction for "%s"' % self.dest
        print '  parser = %s' % id(parser)
        print '  values = %r' % values
        print '  option_string = %r' % option_string
        
        # Do some arbitrary processing of the input values
        if isinstance(values, list):
            values = [ v.upper() for v in values ]
        else:
            values = values.upper()
        # Save the results in the namespace using the destination
        # variable given to our constructor.
        setattr(namespace, self.dest, values)

if __name__ == "__main__":
    Streets4MPI()

