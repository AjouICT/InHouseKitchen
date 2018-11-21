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
       hostList=data;
    }
});

function refreshMap() {
    if (markerClusterer) {
        markerClusterer.clearMarkers();
    }

    var locations = [
        [hostList[0].menusInfo.name, hostList[0].location.latitude, hostList[0].location.longitude, 1],
        [hostList[1].menusInfo.name, hostList[1].location.latitude, hostList[1].location.longitude, 2],
        [hostList[2].menusInfo.name, hostList[2].location.latitude, hostList[2].location.longitude, 3],
        [hostList[3].menusInfo.name, hostList[3].location.latitude, hostList[3].location.longitude, 4],
        [hostList[4].menusInfo.name, hostList[4].location.latitude, hostList[4].location.longitude, 5],
        [hostList[5].menusInfo.name, hostList[5].location.latitude, hostList[5].location.longitude, 6],
        [hostList[6].menusInfo.name, hostList[6].location.latitude, hostList[6].location.longitude, 7],
        [hostList[7].menusInfo.name, hostList[7].location.latitude, hostList[7].location.longitude, 8],
        [hostList[8].menusInfo.name, hostList[8].location.latitude, hostList[8].location.longitude, 9],
        [hostList[9].menusInfo.name, hostList[9].location.latitude, hostList[9].location.longitude, 10],
        [hostList[10].menusInfo.name, hostList[10].location.latitude, hostList[10].location.longitude, 11],
        [hostList[11].menusInfo.name, hostList[11].location.latitude, hostList[11].location.longitude, 12],
        [hostList[12].menusInfo.name, hostList[12].location.latitude, hostList[12].location.longitude, 13],
        [hostList[13].menusInfo.name, hostList[13].location.latitude, hostList[13].location.longitude, 14],
        [hostList[14].menusInfo.name, hostList[14].location.latitude, hostList[14].location.longitude, 15]
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
        maxZoom: 17,
        gridSize: 100,
    });
    google.maps.event.addListener(MarkerClusterer, 'clusterclick', function(cluster) {
        map.setCenter(cluster.getCenter());
        map.setZoom(map.getZoom() + 2);
    });

}

function initialize() {
    map = new google.maps.Map(document.getElementById('map'), {
        zoom: 16,
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