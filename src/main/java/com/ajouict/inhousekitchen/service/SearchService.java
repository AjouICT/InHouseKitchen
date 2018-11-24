package com.ajouict.inhousekitchen.service;

import com.ajouict.inhousekitchen.domain.Host;
import com.ajouict.inhousekitchen.domain.SearchRepository;
import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    @Autowired
    SearchRepository searchRepository;

    public List<Host> hostList(){
        return searchRepository.findAll();
    }

    public String geocoding(String location1){
        //String location="Ajou University";
        Geocoder geocoder = new Geocoder();
        //setAddress : 변환하려는 주소(아주대학교 팔달관)
        //setLangua : 인코딩 설정
        GeocoderRequest geocoderRequest=new GeocoderRequestBuilder().setAddress(location).setLanguage("ko").getGeocoderRequest();
        GeocodeResponse geocoderResponse=geocoder.geocode(geocoderRequest);
        System.out.println("df"+geocoderResponse);
        String coordStr=null;
            if(geocoderResponse.getStatus() == GeocoderStatus.OK & !geocoderResponse.getResults().isEmpty()){
                GeocoderResult geocoderResult=geocoderResponse.getResults().iterator().next();
                LatLng latitudeLongitude=geocoderResult.getGeometry().getLocation();
                Float[] coords= new Float[2];
                coords[0]=latitudeLongitude.getLat().floatValue();
                coords[1]=latitudeLongitude.getLng().floatValue();
                coordStr=Float.toString(coords[0])+"&"+Float.toString(coords[1]);
            }

        return coordStr;
    }

}
