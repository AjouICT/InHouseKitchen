var markerClusterer = null;
var map = null;
var imageUrl = 'http://chart.apis.google.com/chart?cht=mm&chs=24x32&' +
    'chco=FFFFFF,008CFF,000000&ext=.png';
var hostList=null;
$.ajax({
    type:"get",
    url:"/search/getHostList",
    async:false,
    success:function(data){
        alert(data[0].latitude);
        hostList=data;
    }

});

function refreshMap() {
    if (markerClusterer) {
        markerClusterer.clearMarkers();
    }

   /* var locations = [
        [hostList[0].menuName, 37.285691, 127.046324, 4],
        [hostList[1].menuName, 37.284748, 127.041603, 5],
        [hostList[2].menuName, 37.285444, 127.039994, 3],
        [hostList[3].menuName, 37.281968, 127.041908, 2],
        [hostList[4].menuName, 37.281723, 127.044063, 1],
        [hostList[5].menuName, 37.281714, 127.0000, 6],
        [hostList[6].menuName, 37.282226, 127.047534, 7],
        [hostList[7].menuName, 37.284346, 127.044353, 8],
        [hostList[8].menuName, 37.284534, 127.045746, 9],
        [hostList[9].menuName, 37.286489, 127.045682, 10],
        [hostList[10].menuName, 37.285396, 127.046637, 11],
        [hostList[11].menuName, 37.284427, 127.047919, 12],
        [hostList[12].menuName, 37.284009, 127.045945, 13],
        [hostList[13].menuName, 37.283053, 127.047672, 14],
        [hostList[14].menuName, 37.284299, 127.043295, 15]
    ];
*/
    var locations = [
        [hostList[0].menuName, hostList[0].latitude, hostList[0].longitude, 4],
        [hostList[1].menuName, hostList[1].latitude, hostList[1].longitude, 5],
        [hostList[2].menuName, hostList[2].latitude, hostList[2].longitude, 3],
        [hostList[3].menuName, hostList[3].latitude, hostList[3].longitude, 2],
        [hostList[4].menuName, hostList[4].latitude, hostList[4].longitude, 1],
        [hostList[5].menuName, hostList[5].latitude, hostList[5].longitude, 6],
        [hostList[6].menuName, hostList[6].latitude, hostList[6].longitude, 7],
        [hostList[7].menuName, hostList[7].latitude, hostList[7].longitude, 8],
        [hostList[8].menuName, hostList[8].latitude, hostList[8].longitude, 9],
        [hostList[9].menuName, hostList[9].latitude, hostList[9].longitude, 10],
        [hostList[10].menuName, hostList[10].latitude, hostList[10].longitude, 11],
        [hostList[11].menuName, hostList[11].latitude, hostList[11].longitude, 12],
        [hostList[12].menuName, hostList[12].latitude, hostList[12].longitude, 13],
        [hostList[13].menuName, hostList[13].latitude, hostList[13].longitude, 14],
        [hostList[14].menuName, hostList[14].latitude, hostList[14].longitude, 15]
    ];

    var markers = [];
    var infowindow = new google.maps.InfoWindow();
    options = {
        cssClass: 'custom-pin'
    };

    var markerImage = new google.maps.MarkerImage(imageUrl,
        new google.maps.Size(24, 32));

    for (var i = 0; i < locations.length; ++i) {
        var latLng = new google.maps.LatLng(locations[i][1], locations[i][2]);
        var marker = new google.maps.Marker({
            position: latLng,
            draggable: false,
            icon: '../dist/images/pin-1.png' //markerImage
        })
        google.maps.event.addListener(marker, 'click', (function(marker, i) {
            return function() {
                /*var content = '<div class="card lis-brd-light text-center text-lg-left lis-info lis-relative"><a href="#"><div class="lis-grediant grediant-tb-light lis-relative modImage rounded"><img src="' + data.photos[i].photo_file_url +
                    '" class="img-fluid rounded" width="300"></div><div class="lis-absolute lis-left-20 lis-top-20 lis-bg4 lis-icon lis-rounded-circle-50 text-center"><h4 class="text-white mb-0 lis-line-height-2_5"><i class="icofont icofont-fast-food"></i></h4></div></a><div class="hover-text lis-absolute lis-left-20 lis-bottom-20 lis-font-roboto text-white text-left"><h6 class="text-white mb-0">' +
                    data.photos[i].photo_title + '</h6></div></div>';*/
                infowindow.setContent(locations[i][0]);
                infowindow.open(map, marker);
            }
        })(marker, i));

        markers.push(marker);
    }
    markerClusterer = new MarkerClusterer(map, markers, {
        maxZoom: 16,
        gridSize: 100,
    });
    google.maps.event.addListener(MarkerClusterer, 'clusterclick', function(cluster) {
        map.setCenter(cluster.getCenter());
        map.setZoom(map.getZoom() + 2);
    });

}

function initialize() {
    map = new google.maps.Map(document.getElementById('map'), {
        zoom: 15,
        center: new google.maps.LatLng(37.283117, 127.046312),
        mapTypeId: google.maps.MapTypeId.ROADMAP,
        styles: [{
            "elementType": "geometry",
            "stylers": [{
                "color": "#f5f5f5"
            }]
        },
            {
                "elementType": "labels.icon",
                "stylers": [{
                    "visibility": "off"
                }]
            },
            {
                "elementType": "labels.text.fill",
                "stylers": [{
                    "color": "#616161"
                }]
            },
            {
                "elementType": "labels.text.stroke",
                "stylers": [{
                    "color": "#f5f5f5"
                }]
            },
            {
                "featureType": "administrative.land_parcel",
                "elementType": "labels.text.fill",
                "stylers": [{
                    "color": "#bdbdbd"
                }]
            },
            {
                "featureType": "poi",
                "elementType": "geometry",
                "stylers": [{
                    "color": "#eeeeee"
                }]
            },
            {
                "featureType": "poi",
                "elementType": "labels.text.fill",
                "stylers": [{
                    "color": "#757575"
                }]
            },
            {
                "featureType": "poi.park",
                "elementType": "geometry",
                "stylers": [{
                    "color": "#e5e5e5"
                }]
            },
            {
                "featureType": "poi.park",
                "elementType": "labels.text.fill",
                "stylers": [{
                    "color": "#9e9e9e"
                }]
            },
            {
                "featureType": "road",
                "elementType": "geometry",
                "stylers": [{
                    "color": "#ffffff"
                }]
            },
            {
                "featureType": "road.arterial",
                "elementType": "labels.text.fill",
                "stylers": [{
                    "color": "#757575"
                }]
            },
            {
                "featureType": "road.highway",
                "elementType": "geometry",
                "stylers": [{
                    "color": "#dadada"
                }]
            },
            {
                "featureType": "road.highway",
                "elementType": "labels.text.fill",
                "stylers": [{
                    "color": "#616161"
                }]
            },
            {
                "featureType": "road.local",
                "elementType": "labels.text.fill",
                "stylers": [{
                    "color": "#9e9e9e"
                }]
            },
            {
                "featureType": "transit.line",
                "elementType": "geometry",
                "stylers": [{
                    "color": "#e5e5e5"
                }]
            },
            {
                "featureType": "transit.station",
                "elementType": "geometry",
                "stylers": [{
                    "color": "#eeeeee"
                }]
            },
            {
                "featureType": "water",
                "elementType": "geometry",
                "stylers": [{
                    "color": "#c9c9c9"
                }]
            },
            {
                "featureType": "water",
                "elementType": "labels.text.fill",
                "stylers": [{
                    "color": "#9e9e9e"
                }]
            }
        ]
    });
    refreshMap();
}

function clearClusters(e) {
    e.preventDefault();
    e.stopPropagation();
    markerClusterer.clearMarkers();
}

google.maps.event.addDomListener(window, 'load', initialize);