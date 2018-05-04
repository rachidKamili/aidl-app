package me.kamili.rachid.server;

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.os.IBinder;
import android.os.RemoteException;

import java.util.ArrayList;
import java.util.List;

import me.kamili.rachid.server.utils.LegendGroupContract;
import me.kamili.rachid.server.utils.LegendGroupDataBaseHelper;
import me.kamili.rachid.server.utils.LegendGroupFactory;

public class MyService extends Service {
    private SQLiteDatabase db;
    private List<LegendGroup> mLegendList = new ArrayList<>();
    private final IMyService.Stub mBinder = new IMyService.Stub() {
        @Override
        public List<LegendGroup> getAll() throws RemoteException {


            SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
            queryBuilder.setTables(LegendGroupContract.LEGENDGROUP_TABLE_NAME);
            Cursor cursor = queryBuilder.query(db, null, null, null, null, null, null);

            while (cursor.moveToNext()) {
                LegendGroup legendGroup = new LegendGroup(
                        cursor.getString(cursor.getColumnIndex(LegendGroupContract.COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(LegendGroupContract.COLUMN_SLOGAN)),
                        cursor.getString(cursor.getColumnIndex(LegendGroupContract.COLUMN_DESCRIPTION)),
                        cursor.getString(cursor.getColumnIndex(LegendGroupContract.COLUMN_LOGO)),
                        cursor.getString(cursor.getColumnIndex(LegendGroupContract.COLUMN_IMAGE))
                );
                legendGroup.setId(
                        cursor.getLong(cursor.getColumnIndex(LegendGroupContract.COLUMN_ID))
                );
                mLegendList.add(legendGroup);
            }
            cursor.close();

            if (mLegendList.isEmpty()){
                for (LegendGroup legendGroup : LegendGroupFactory.createLegendGroups()){
                    saveLegend(legendGroup);
                }
            }

            return mLegendList;
        }

        @Override
        public void saveLegend(LegendGroup legend) throws RemoteException {

            ContentValues values = new ContentValues();
            values.put(LegendGroupContract.COLUMN_DESCRIPTION, legend.getDescription());
            values.put(LegendGroupContract.COLUMN_IMAGE, legend.getImage());
            values.put(LegendGroupContract.COLUMN_LOGO, legend.getLogo());
            values.put(LegendGroupContract.COLUMN_SLOGAN, legend.getSlogan());
            values.put(LegendGroupContract.COLUMN_NAME, legend.getName());

            long newRowId = db.insert(LegendGroupContract.LEGENDGROUP_TABLE_NAME, null, values);

            legend.setId(newRowId);
            mLegendList.add(legend);
        }
    };

    public MyService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        db = new LegendGroupDataBaseHelper(getApplicationContext()).getWritableDatabase();
        return mBinder;
    }
}
