package br.com.gustavoeguedes.geotraker.service;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

@Service
public class GeoService {

    private final GeometryFactory geometryFactory;

    public GeoService() {
        this.geometryFactory = new GeometryFactory();
    }

    public double calcularDistanciaKm(double lat1, double lon1, double lat2, double lon2) {
        Point p1 = geometryFactory.createPoint(new Coordinate(lon1, lat1));
        Point p2 = geometryFactory.createPoint(new Coordinate(lon2, lat2));
        return p1.distance(p2) / 1000.0;
    }
}