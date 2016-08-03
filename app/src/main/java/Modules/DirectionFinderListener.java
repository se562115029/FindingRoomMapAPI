package Modules;

/**
 * Created by oo on 26/7/2559.
 */
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;
import java.util.List;
import Modules.Route;
public interface DirectionFinderListener {
    void onDirectionFinderStar();
    void onDirectionFinderSuccess(List<Route> route);
}
