var map;

/*
 * MapToolbar
 * a literal object
 *  - act as a container that will share one or more Feature instance
 *  - act as a namespace
 */

var MapToolbar = {
    //reorder index of a poly markers array

    reindex: function (markers) {
        markers.forEach(function (marker, index) {
            marker.index = index;
        });
    },
    //get point at middle distance between 2 point

    getMidPoint: function () {
        var lat = (arguments[0].lat() + arguments[1].lat()) / 2;
        var lng = (arguments[0].lng() + arguments[1].lng()) / 2;
        return new google.maps.LatLng(lat, lng);
    },
    //currently edited feature

    currentFeature: null,
    //add a point to a poly, 'e' can be a click event or a latLng object

    addPoint: function (e, poly, index) {
        var e = (typeof e.latLng !== "undefined") ? e.latLng : e,
            image = new google.maps.MarkerImage('resources/res_jsp2/images/icons/marker-edition.png',
                new google.maps.Size(9, 9),
                new google.maps.Point(0, 0),
                new google.maps.Point(5, 5)),
            imageover = new google.maps.MarkerImage('resources/res_jsp2/images/icons/marker-edition-over.png',
                new google.maps.Size(9, 9),
                new google.maps.Point(0, 0),
                new google.maps.Point(5, 5)),
            path = poly.getPath(),
            index = (typeof index !== "undefined") ? index : path.length,
            markers = (poly.markers) ? poly.markers : new google.maps.MVCArray,
            marker = new google.maps.Marker({
                position: e,
                map: map,
                draggable: true,
                icon: image
            });

        marker.index = index;
        path.insertAt(index, e);
        markers.insertAt(index, marker);
        if (arguments[2]) {
            MapToolbar.reindex(markers);
        }

        //click on a polymarker will delete it

        google.maps.event.addListener(marker, 'click', function () {
            marker.setMap(null);
            markers.removeAt(marker.index);
            path.removeAt(marker.index);
            MapToolbar.reindex(markers);
            if (markers.getLength() === 0) {
                MapToolbar.removeFeature(poly.id);
            }
        });

        google.maps.event.addListener(marker, 'position_changed', function () {
            path.setAt(marker.index, marker.getPosition());
        });

        google.maps.event.addListener(marker, 'dragend', function () {
            //MapToolbar.currentlyDragging = false;
            path.setAt(marker.index, marker.getPosition());
            var position = marker.getPosition(),
                p;

            //get previous point

            if (typeof path.getAt(marker.index - 1) !== "undefined") {
                var m1 = path.getAt(marker.index - 1);
                p = MapToolbar.getMidPoint(position, m1);
                MapToolbar.addPoint(p, poly, marker.index);
            }

            // get next point

            if (typeof path.getAt(marker.index + 1) !== "undefined") {
                var m2 = path.getAt(marker.index + 1);
                p = MapToolbar.getMidPoint(position, m2);
                MapToolbar.addPoint(p, poly, marker.index + 1);
            }
        });

        google.maps.event.addListener(marker, 'mouseover', function () {
            this.setIcon(imageover);
        });

        google.maps.event.addListener(marker, 'mouseout', function () {
            this.setIcon(image);
        });

//        var send_button = document.getElementById("send");
//        send_button.disabled = true;
//        document.getElementById("send").style.backgroundColor = '#000000';

    },
    //append a DOM node to $featureTable

    addFeatureEntry: function (name, color) {
        currentRow_ = document.createElement("tr");

        var colorCell = document.createElement("td");
        currentRow_.appendChild(colorCell);
        colorCell.style.backgroundColor = color;
        colorCell.style.width = "1em";

        var nameCell = document.createElement("td");
        currentRow_.appendChild(nameCell);
        nameCell.innerHTML = name;
        nameCell.onclick = new Function("MapToolbar.setMapCenter('" + name + "')");

        var descriptionCell = document.createElement("td");
        currentRow_.appendChild(descriptionCell);
        this.$featureTable.appendChild(currentRow_);

        var deleteCell = document.createElement("td");
        deleteCell.id = name;
        deleteCell.onclick = new Function("MapToolbar.removeFeature('" + name + "')");
        deleteCell.innerHTML = "<input type=\"button\" class=\"form_send\" style=\"width:40px; height:40px;\" value=\"Usu&#324;\" />";
        currentRow_.appendChild(deleteCell);
        this.$featureTable.appendChild(currentRow_);

        var send_button = document.getElementById("send");
        send_button.disabled = true;
        document.getElementById("send").style.backgroundColor = '#000000';

        return {
            row: currentRow_,
            desc: descriptionCell,
            color: colorCell
        };
    },
    //edition buttons

    buttons: {
        $hand: null,
        $shape: null
    },
    //click event for line and shape edition

    polyClickEvent: null,
    //an array of predefined colors

    colors: [
        ["red", "#ff0000"],
        ["orange", "#ff8800"],
        ["green", "#008000"],
        ["blue", "#000080"],
        ["purple", "#800080"]
    ],
    colorIndex: 0,
    //contains list of overlay that were added to the map
    //and that are displayed on the sidebar

    $featureTable: document.getElementById("featuretbody"),
    Feature: function (type) {
        if (type === "shape") {
            this['poly'](type);
        } else {
            this[type]();
        }
    },
    //contains reference for all features added on the map

    features: {
        placemarkTab: {},
        shapeTab: {},
        overlayTab: {}
    },
    getColor: function (named) {
        return this.colors[(this.colorIndex++) % this.colors.length][named ? 0 : 1];
    },
    getIcon: function (color) {
        var icon = new google.maps.MarkerImage("http://google.com/mapfiles/ms/micons/" + color + ".png",
            new google.maps.Size(32, 32),
            new google.maps.Point(0, 0),
            new google.maps.Point(15, 32)
        );
        return icon;
    },
    //instanciate a new Feature instance and create a reference 

    initFeature: function (type) {
        new MapToolbar.Feature(type);
    },
    //check if a toolbar button is selected

    isSelected: function (el) {
        return (el.className === "selected");
    },
    //the map DOM node container

    placemarkCounter: 0,
    shapeCounter: 0,
    //remove click events used for poly edition/update

    removeClickEvent: function () {
    },
    // remove feature from map

    removeFeature: function (id) {
        var type = id.split('_')[0];
        var feature = MapToolbar.features[type + 'Tab'][id];
        feature.$el.row.parentNode.removeChild(feature.$el.row);
        delete  MapToolbar.features[type + 'Tab'][id];
        switch (type) {
            default:
                feature.markers.forEach(function (marker, index) {
                    marker.setMap(null);
                });
                feature.setMap(null);
                break;
        }
        MapToolbar.select('hand_b');
    },
    //toolbar buttons selection

    select: function (buttonId) {
        MapToolbar.buttons.$hand.className = "unselected";
        MapToolbar.buttons.$shape.className = "unselected";
        document.getElementById(buttonId).className = "selected";
    },
    setMapCenter: function (featureName) {
        var type = featureName.split('_')[0];
        if (type === 'shape') {
            MapToolbar.currentFeature = MapToolbar.features[type + 'Tab'][featureName];
            var point = MapToolbar.currentFeature.getPath().getAt(0);
        }
        MapToolbar.select(type + '_b');
        map.setCenter(point);
    },
    stopEditing: function () {
        this.removeClickEvent();
        this.select("hand_b");

        $(document).data('ilosc_punktow', '' + MapToolbar.features.shapeTab.shape_1.latLngs.b[0].length);

        var final_string_a = "";
        var final_string_b = "";
        for (var i = 0; i < MapToolbar.features.shapeTab.shape_1.getPath().getLength(); i++) {
            final_string_a = 'p' + parseInt(i) + '_' + 'a';
            $(document).data('' + final_string_a, '' + MapToolbar.features.shapeTab.shape_1.getPath().getAt(i).lat());
            final_string_b = 'p' + parseInt(i) + '_' + 'b';
            $(document).data('' + final_string_b, '' + MapToolbar.features.shapeTab.shape_1.getPath().getAt(i).lng());
        }

        var send_button = document.getElementById("send");
        send_button.disabled = false;
        document.getElementById("send").style.backgroundColor = '#085299';
    },
    updateMarker: function (marker, cells, color) {
        if (color) {
            marker.setIcon(MapToolbar.getIcon(color));
        }
        var latlng = marker.getPosition();
        cells.desc.innerHTML = "(" + Math.round(latlng.b * 100) / 100 + ", " + Math.round(latlng.c * 100) / 100 + ")";
    }
};

MapToolbar.Feature.prototype.poly = function (type) {
    var color = MapToolbar.getColor(false),
        path = new google.maps.MVCArray,
        poly,
        self = this,
        el = type + "_b";

    if (type === "shape") {
        poly = self.createShape({
            strokeWeight: 3,
            fillColor: color
        }, path);
    }

    poly.markers = new google.maps.MVCArray;

    if (MapToolbar.isSelected(document.getElementById(el)))
        return;
    MapToolbar.select(el);
    MapToolbar.currentFeature = poly;
    poly.setMap(map);
    if (!poly.$el) {
        ++MapToolbar[type + "Counter"];
        poly.id = type + '_' + MapToolbar[type + "Counter"];
        poly.$el = MapToolbar.addFeatureEntry(poly.id, color);
        MapToolbar.features[type + "Tab"][poly.id] = poly;
    }
};

MapToolbar.Feature.prototype.createShape = function (opts, path) {
    var poly;
    poly = new google.maps.Polygon({
        strokeWeight: opts.strokeWeight,
        fillColor: opts.fillColor
    });
    poly.setPaths(new google.maps.MVCArray([path]));
    return poly;
};

function initialize(container) {
    var options = {
        mapTypeControlOptions: {
            mapTypeIds: [google.maps.MapTypeId.ROADMAP, google.maps.MapTypeId.TERRAIN],
            style: google.maps.MapTypeControlStyle.DROPDOWN_MENU
        }
    };
    map = new google.maps.Map(document.getElementById('map'));
    map.setCenter(new google.maps.LatLng(52.231797, 21.006095));
    map.setZoom(17);
    map.setMapTypeId(google.maps.MapTypeId.ROADMAP);

    with (MapToolbar) {
        with (buttons) {
            $hand = document.getElementById("hand_b");
            $shape = document.getElementById("shape_b");
        }
        $featureTable = document.getElementById("featuretbody");
        select("hand_b");
    }

    MapToolbar.polyClickEvent = google.maps.event.addListener(map, 'click', function (event) {
        if (!MapToolbar.isSelected(MapToolbar.buttons.$shape))
            return;
        if (MapToolbar.currentFeature) {
            MapToolbar.addPoint(event, MapToolbar.currentFeature);
        }
    });

    google.maps.event.addListener(MapToolbar, 'polygoncomplete', function (polygon) {
        var coordinates = (polygon.getPath().getArray());
    });
}

