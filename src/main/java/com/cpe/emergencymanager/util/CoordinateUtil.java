package com.cpe.emergencymanager.util;

import com.cpe.emergencymanager.model.LocalizedEntity;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.locationtech.jts.geom.*;

import java.util.List;

public class CoordinateUtil {

    /**
     * Fonction permettant de calculer la distance entre deux points
     * @param lat1 latitude point 1
     * @param lon1 longitude point 1
     * @param lat2 latitude point 2
     * @param lon2 longitude point 2
     * @param unit unité de mesure 'K' pour kilomètres ou 'N'
     * @return
     */
    public static double distance(double lat1, double lon1, double lat2, double lon2, char unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
        dist = Math.acos(dist);
        dist = Math.toDegrees(dist);
        dist = dist * 60 * 1.1515;
        if (unit == 'K') {
            dist = dist * 1.609344;
        } else if (unit == 'N') {
            dist = dist * 0.8684;
        }
        return (dist);
    }

    public static double distance(LocalizedEntity entity1, LocalizedEntity entity2, char unit) {
        return distance(entity1.getLatitude(), entity1.getLongitude(), entity2.getLatitude(), entity2.getLongitude(), unit);
    }

    /**
     * Créer un Polygon à partir d'une liste de point et renvoi les coordonnées de son centre
     * @param coordinates
     * @return
     */
    public static Coordinate getCentroidFromCoordinates(List<Coordinate> coordinates) {
        GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();

        Coordinate[] coords  = new Coordinate[coordinates.size()];
        for (int i = 0; i < coordinates.size(); i++) {
            coords[i] = coordinates.get(i);
        }
        LinearRing ring = geometryFactory.createLinearRing( coords );
        LinearRing holes[] = null;
        Polygon polygon = geometryFactory.createPolygon(ring, holes);
        return polygon.getCentroid().getCoordinate();
    }
}
