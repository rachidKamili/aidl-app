// IMyService.aidl
package me.kamili.rachid.server;

import java.util.List;
import me.kamili.rachid.client.LegendGroup;


interface IMyService {
    List<LegendGroup> getAll();
    void saveLegend(in LegendGroup legend);
}
