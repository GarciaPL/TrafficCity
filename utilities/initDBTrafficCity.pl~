#!/usr/bin/perl
$mongoDB = "/home/lukasz/MongoDB/mongodb-linux-x86_64-2.6.5/bin/";
print "Go to MongoDB catalog\n";
chdir($mongoDB);
print "Starting MongoDB\n";
my @mongodb_info = `./mongod --dbpath /home/lukasz/MongoDB/TrafficCity/data/db/ --port 27017`;
print @mongodb_info;
