package com.cpm.Marico.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.cpm.Marico.R;
import com.cpm.Marico.delegates.CoverageBean;
import com.cpm.Marico.getterSetter.AnswerChecklistGetterSetter;
import com.cpm.Marico.getterSetter.BackofStoreGetterSetter;
import com.cpm.Marico.getterSetter.BrandMaster;
import com.cpm.Marico.getterSetter.BrandMasterGetterSetter;
import com.cpm.Marico.getterSetter.CategoryMaster;
import com.cpm.Marico.getterSetter.CategoryMasterGetterSetter;
import com.cpm.Marico.getterSetter.ChecklistAnswer;
import com.cpm.Marico.getterSetter.ChecklistGetterSetter;
import com.cpm.Marico.getterSetter.ChecklistMaster;
import com.cpm.Marico.getterSetter.CommonChillerDataGetterSetter;
import com.cpm.Marico.getterSetter.DisplayMaster;
import com.cpm.Marico.getterSetter.DisplayMasterGetterSetter;
import com.cpm.Marico.getterSetter.FeedbackMaster;
import com.cpm.Marico.getterSetter.FeedbackMasterGetterSetter;
import com.cpm.Marico.getterSetter.FocusProductGetterSetter;
import com.cpm.Marico.getterSetter.GeotaggingBeans;
import com.cpm.Marico.getterSetter.GroomingGetterSetter;
import com.cpm.Marico.getterSetter.HeaderGetterSetter;
import com.cpm.Marico.getterSetter.JCPGetterSetter;
import com.cpm.Marico.getterSetter.JarGetterSetter;
import com.cpm.Marico.getterSetter.JourneyPlan;
import com.cpm.Marico.getterSetter.MappingCategoryChecklist;
import com.cpm.Marico.getterSetter.MappingCategoryChecklistGetterSetter;
import com.cpm.Marico.getterSetter.MappingFocusSku;
import com.cpm.Marico.getterSetter.MappingFocusSkuGetterSetter;
import com.cpm.Marico.getterSetter.MappingInitiativeGetterSetter;
import com.cpm.Marico.getterSetter.MappingMenu;
import com.cpm.Marico.getterSetter.MappingMenuChecklist;
import com.cpm.Marico.getterSetter.MappingMenuGetterSetter;
import com.cpm.Marico.getterSetter.MappingPaidVisibility;
import com.cpm.Marico.getterSetter.MappingPaidVisibilityGetterSetter;
import com.cpm.Marico.getterSetter.MappingPosm;
import com.cpm.Marico.getterSetter.MappingPosmGetterSetter;
import com.cpm.Marico.getterSetter.MappingPromotion;
import com.cpm.Marico.getterSetter.MappingPromotionGetterSetter;
import com.cpm.Marico.getterSetter.MappingSampling;
import com.cpm.Marico.getterSetter.MappingSamplingGetterSetter;
import com.cpm.Marico.getterSetter.MappingShareOfShelf;
import com.cpm.Marico.getterSetter.MappingShareOfShelfGetterSetter;
import com.cpm.Marico.getterSetter.MappingTesterStock;
import com.cpm.Marico.getterSetter.MappingTesterStockGetterSetter;
import com.cpm.Marico.getterSetter.MappingVisibilityInitiative;
import com.cpm.Marico.getterSetter.MappingVisicooler;
import com.cpm.Marico.getterSetter.MappingVisicoolerGetterSetter;
import com.cpm.Marico.getterSetter.MarketIntelligenceGetterSetter;
import com.cpm.Marico.getterSetter.MenuMaster;
import com.cpm.Marico.getterSetter.MenuMasterGetterSetter;
import com.cpm.Marico.getterSetter.NonCategoryReason;
import com.cpm.Marico.getterSetter.NonCategoryReasonGetterSetter;
import com.cpm.Marico.getterSetter.NonExecutionReason;
import com.cpm.Marico.getterSetter.NonExecutionReasonGetterSetter;
import com.cpm.Marico.getterSetter.NonWindowReason;
import com.cpm.Marico.getterSetter.NonWindowReasonGetterSetter;
import com.cpm.Marico.getterSetter.NonWorkingReason;
import com.cpm.Marico.getterSetter.NonWorkingReasonGetterSetter;
import com.cpm.Marico.getterSetter.PerformancePage;
import com.cpm.Marico.getterSetter.PerformancePageGetterSetter;
import com.cpm.Marico.getterSetter.PosmMaster;
import com.cpm.Marico.getterSetter.PosmMasterGetterSetter;
import com.cpm.Marico.getterSetter.SampledGetterSetter;
import com.cpm.Marico.getterSetter.SamplingChecklist;
import com.cpm.Marico.getterSetter.SamplingChecklistGetterSetter;
import com.cpm.Marico.getterSetter.ShareOfShelfGetterSetter;
import com.cpm.Marico.getterSetter.SkuMaster;
import com.cpm.Marico.getterSetter.SkuMasterGetterSetter;
import com.cpm.Marico.getterSetter.StockNewGetterSetter;
import com.cpm.Marico.getterSetter.StoreProfileGetterSetter;
import com.cpm.Marico.getterSetter.StoreTypeMaster;
import com.cpm.Marico.getterSetter.StoreTypeMasterGetterSetter;
import com.cpm.Marico.getterSetter.SubCategoryMaster;
import com.cpm.Marico.getterSetter.SubCategoryMasterGetterSetter;
import com.cpm.Marico.getterSetter.VisiColoersGetterSetter;
import com.cpm.Marico.getterSetter.WindowCheckAnswerGetterSetter;
import com.cpm.Marico.getterSetter.WindowChecklist;
import com.cpm.Marico.getterSetter.WindowChecklistAnswer;
import com.cpm.Marico.getterSetter.WindowChecklistGetterSetter;
import com.cpm.Marico.getterSetter.WindowMaster;
import com.cpm.Marico.getterSetter.WindowNonReasonGetterSetter;
import com.cpm.Marico.utilities.CommonString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MaricoDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MaricoDB1";
    public static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;
    Context context;

    public MaricoDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public void open() {
        try {
            db = this.getWritableDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            //jeevan
            db.execSQL(CommonString.CREATE_TABLE_COVERAGE_DATA);
            db.execSQL(CommonString.CREATE_TABLE_STORE_GEOTAGGING);
            //marico
            db.execSQL(CommonString.CREATE_TABLE_STOCK_IMAGE);
            db.execSQL(CommonString.CREATE_TABLE_insert_OPENINGHEADER_DATA);
            db.execSQL(CommonString.CREATE_TABLE_STOCK_DATA);
            db.execSQL(CommonString.CREATE_TABLE_SHARE_OF_SHELF_IMAGE);
            db.execSQL(CommonString.CREATE_TABLE_SHARE_OF_SHELF_DATA);
            db.execSQL(CommonString.CREATE_TABLE_INSERT_MARKET_INTELLIGENCE_DATA);
            db.execSQL(CommonString.CREATE_TABLE_GROOMING_IMAGE_DATA);
            db.execSQL(CommonString.CREATE_TABLE_PROMOTION_HEADER_DATA);
            db.execSQL(CommonString.CREATE_TABLE_PROMOTION_CHILD_DATA);
            db.execSQL(CommonString.CREATE_TABLE_PAID_VISIBILITY_HEADER_DATA);
            db.execSQL(CommonString.CREATE_TABLE_PAID_VISIBILITY_CHILD_DATA);
            db.execSQL(CommonString.CREATE_TABLE_INSERT_SAMPLED_DATA);
            db.execSQL(CommonString.CREATE_TABLE_INSERT_SAMPLED_CHECKLIT_DATA);
            db.execSQL(CommonString.CREATE_TABLE_TESTER_STOCK);


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public int createtable(String sqltext) {
        try {
            db.execSQL(sqltext);
            return 1;
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }


    public void deleteTableWithStoreID(String storeid) {
        db.delete(CommonString.TABLE_COVERAGE_DATA, CommonString.KEY_STORE_ID + "='" + storeid + "'", null);
        db.delete(CommonString.TABLE_STORE_GEOTAGGING, CommonString.KEY_STORE_ID + "='" + storeid + "'", null);
        db.delete(CommonString.TABLE_STOCK_IMAGE, CommonString.KEY_STORE_ID + "='" + storeid + "'", null);
        db.delete(CommonString.TABLE_INSERT_OPENINGHEADER_DATA, CommonString.KEY_STORE_ID + "='" + storeid + "'", null);
        db.delete(CommonString.TABLE_STOCK_DATA, CommonString.KEY_STORE_ID + "='" + storeid + "'", null);
        db.delete(CommonString.TABLE_CATEGORY_SHARE_OF_SHELF_IMAGE, CommonString.KEY_STORE_ID + "='" + storeid + "'", null);
        db.delete(CommonString.TABLE_SHARE_OF_SHELF_FACING_DATA, CommonString.KEY_STORE_ID + "='" + storeid + "'", null);
        db.delete(CommonString.TABLE_INSERT_MARKET_INTELLI_DATA, CommonString.KEY_STORE_ID + "='" + storeid + "'", null);
        db.delete(CommonString.TABLE_GROOMING_IMAGE_DATA, CommonString.KEY_STORE_ID + "='" + storeid + "'", null);
        db.delete(CommonString.TABLE_INSERT_SAMPLED_CHCEKLIST_DATA, CommonString.KEY_STORE_ID + "='" + storeid + "'", null);
        db.delete(CommonString.TABLE_INSERT_SAMPLED_DATA, CommonString.KEY_STORE_ID + "='" + storeid + "'", null);
        db.delete(CommonString.TABLE_PROMOTION_HEADER_DATA, CommonString.KEY_STORE_ID + "='" + storeid + "'", null);
        db.delete(CommonString.TABLE_PROMOTION_CHILD_DATA, CommonString.KEY_STORE_ID + "='" + storeid + "'", null);
        db.delete(CommonString.TABLE_PAID_VISIBILITY_HEADER_DATA, CommonString.KEY_STORE_ID + "='" + storeid + "'", null);
        db.delete(CommonString.TABLE_PAID_VISIBILITY_CHILD_DATA, CommonString.KEY_STORE_ID + "='" + storeid + "'", null);
        db.delete(CommonString.TABLE_TESTER_STOCK_DATA, CommonString.KEY_STORE_ID + "='" + storeid + "'", null);

    }

    public void deletePreviousUploadedData(String visit_date) {
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT  * from COVERAGE_DATA where VISIT_DATE < '" + visit_date + "'", null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                int icount = dbcursor.getCount();
                dbcursor.close();
                if (icount > 0) {
                    db.delete(CommonString.TABLE_COVERAGE_DATA, null, null);
                    db.delete(CommonString.TABLE_STORE_GEOTAGGING, null, null);

                    db.delete(CommonString.TABLE_STOCK_IMAGE, null,null);
                    db.delete(CommonString.TABLE_INSERT_OPENINGHEADER_DATA, null, null);
                    db.delete(CommonString.TABLE_STOCK_DATA, null, null);
                    db.delete(CommonString.TABLE_CATEGORY_SHARE_OF_SHELF_IMAGE, null, null);
                    db.delete(CommonString.TABLE_SHARE_OF_SHELF_FACING_DATA, null, null);
                    db.delete(CommonString.TABLE_INSERT_MARKET_INTELLI_DATA, null, null);
                    db.delete(CommonString.TABLE_GROOMING_IMAGE_DATA, null, null);
                    db.delete(CommonString.TABLE_INSERT_SAMPLED_CHCEKLIST_DATA, null, null);
                    db.delete(CommonString.TABLE_INSERT_SAMPLED_DATA, null, null);
                    db.delete(CommonString.TABLE_PROMOTION_HEADER_DATA, null, null);
                    db.delete(CommonString.TABLE_PROMOTION_CHILD_DATA, null, null);
                    db.delete(CommonString.TABLE_PAID_VISIBILITY_HEADER_DATA, null, null);
                    db.delete(CommonString.TABLE_PAID_VISIBILITY_CHILD_DATA, null, null);
                    db.delete(CommonString.TABLE_TESTER_STOCK_DATA, null, null);
                }
                dbcursor.close();
            }

        } catch (Exception e) {

        }
    }

    public void updateStatus(String id, String status) {
        ContentValues values = new ContentValues();
        try {
            values.put("Geo_Tag", status);
            db.update(CommonString.TABLE_Journey_Plan, values, "Store_Id" + "='" + id + "'", null);
        } catch (Exception ex) {
        }
    }

    public long InsertSTOREgeotag(String storeid, double lat, double longitude, String path, String status) {
        ContentValues values = new ContentValues();
        try {
            values.put("STORE_ID", storeid);
            values.put("LATITUDE", Double.toString(lat));
            values.put("LONGITUDE", Double.toString(longitude));
            values.put("FRONT_IMAGE", path);
            values.put("GEO_TAG", status);
            values.put("STATUS", status);

            return db.insert(CommonString.TABLE_STORE_GEOTAGGING, null, values);

        } catch (Exception ex) {
            Log.d("Database Exception ", ex.toString());
            return 0;
        }
    }

    public long updateInsertedGeoTagStatus(String id, String status) {
        ContentValues values = new ContentValues();
        try {
            values.put("GEO_TAG", status);
            values.put("STATUS", status);
            return db.update(CommonString.TABLE_STORE_GEOTAGGING, values, CommonString.KEY_STORE_ID + "='" + id + "'", null);
        } catch (Exception ex) {
            return 0;
        }
    }

    public ArrayList<CoverageBean> getCoverageDataPrevious(String visitdate) {

        ArrayList<CoverageBean> list = new ArrayList<CoverageBean>();
        Cursor dbcursor = null;

        try {

            dbcursor = db.rawQuery("SELECT  * from " + CommonString.TABLE_COVERAGE_DATA + " where "
                            + CommonString.KEY_VISIT_DATE + " <> '" + visitdate + "'",
                    null);

            if (dbcursor != null) {

                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    CoverageBean sb = new CoverageBean();

                    sb.setStoreId(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
                    sb.setUserId(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_USER_ID)));
                    sb.setVisitDate(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_VISIT_DATE)));
                    sb.setLatitude(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_LATITUDE)));
                    sb.setLongitude(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_LONGITUDE)));
                    sb.setImage(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_IMAGE)));
                    sb.setReason(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_REASON)));
                    sb.setReasonid(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_REASON_ID)));
                    sb.setMID(Integer.parseInt(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_ID))));
                    //sb.setCkeckout_image(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_CHECKOUT_IMAGE)));

                    sb.setRemark(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_COVERAGE_REMARK)));

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {
            Log.d("Exception get JCP!", e.toString());
            return list;
        }
        return list;
    }


    public ArrayList<CoverageBean> getCoverageWithStoreIDAndVisitDate_Data(String store_id, String visitdate) {

        ArrayList<CoverageBean> list = new ArrayList<CoverageBean>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT  * from " + CommonString.TABLE_COVERAGE_DATA + " where " + CommonString.KEY_STORE_ID + "='" + store_id + "' AND " + CommonString.KEY_VISIT_DATE + "='" + visitdate + "'", null);

            if (dbcursor != null) {

                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    CoverageBean sb = new CoverageBean();

                    sb.setStoreId(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
                    sb.setVisitDate((((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_VISIT_DATE))))));
                    sb.setLatitude(((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_LATITUDE)))));
                    sb.setLongitude(((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_LONGITUDE)))));
                    sb.setImage((((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_IMAGE))))));
                    sb.setReasonid((((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_REASON_ID))))));
                    if (dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_COVERAGE_REMARK)) == null) {
                        sb.setRemark("");
                    } else {
                        sb.setRemark((((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_COVERAGE_REMARK))))));
                    }
                    sb.setReason((((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_REASON))))));

                   /* sb.setInTime(((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_IN_TIME)))));
                    sb.setOutTime(((dbcursor.getString(dbcursor
                            .getColumnIndexOrThrow(CommonString.KEY_OUT_TIME)))));*/
                    sb.setMID(Integer.parseInt(((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_ID))))));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {
            Log.d("Exception get JCP!", e.toString());
            return list;
        }
        return list;
    }

    public ArrayList<WindowNonReasonGetterSetter> getWindowNonReasonData() {
        ArrayList<WindowNonReasonGetterSetter> list = new ArrayList<>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("Select 0 as WReason_Id,'Select' as WReason union SELECT WReason_Id,WReason FROM Non_Window_Reason", null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    WindowNonReasonGetterSetter sb = new WindowNonReasonGetterSetter();
                    sb.setWREASON_CD(dbcursor.getString(dbcursor.getColumnIndexOrThrow("WReason_Id")));
                    sb.setWREASON(dbcursor.getString(dbcursor.getColumnIndexOrThrow("WReason")));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {

            return list;
        }
        return list;
    }


    public ArrayList<WindowMaster> getWindowListData(JourneyPlan jcp) {
        Log.d("Fetchecklidata->Start<-", "-");
        ArrayList<WindowMaster> list = new ArrayList<>();
        Cursor dbcursor = null;
        try {

            dbcursor = db.rawQuery("select distinct wmap.Brand_id as Brand_id,bm.Brand as Brand, wm.Window_id as Window_id, wm.window as window,wm.Window_Image as Window_Image, IFNULL(d.Existornot,'false') as EXISTORNOT, IFNULL(d.window_Image,'') as WINDOW_IMAGE,IFNULL( d.Reason_Id,'0') as REASON_ID from window_master wm inner join Mapping_Visibility_Initiatives_New wmap on wm.window_id = wmap.window_id" +
                    " inner join Brand_Master bm on wmap.Brand_Id = bm.Brand_Id left join" +
                    " (select * from WINDOWS_DATA Where Store_Id = " + jcp.getStoreId() + " and Date ='" + jcp.getVisitDate() + "') as d on wm.Window_Id = d.WINDOW_CD and wmap.Brand_Id=d.BRAND_ID" +
                    "  where state_id = " + jcp.getStateId() + " and Distributor_id = " + jcp.getDistributorId() + " and Store_Type_Id = " + jcp.getStoreTypeId() + " and Classification_Id = " + jcp.getClassificationId() + " and Store_Category_Id = " + jcp.getStoreCategoryId() + "", null);


            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    WindowMaster ch = new WindowMaster();
                    ch.setBrand_Id(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Brand_id")));
                    ch.setBrand(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Brand")));
                    ch.setWindowId(dbcursor.getInt(dbcursor.getColumnIndexOrThrow("Window_id")));
                    ch.setWindow(dbcursor.getString(dbcursor.getColumnIndexOrThrow("window")));
                    ch.setExist(Boolean.parseBoolean(dbcursor.getString(dbcursor.getColumnIndexOrThrow("EXISTORNOT"))));
                    ch.setImage(dbcursor.getString(dbcursor.getColumnIndexOrThrow("WINDOW_IMAGE")));
                    ch.setReasonId(Integer.parseInt(dbcursor.getString(dbcursor.getColumnIndexOrThrow("REASON_ID"))));
                    ch.setWindow_Image_refrance(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Window_Image")));
                    list.add(ch);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {
            Log.d("Exc get windows list!", e.toString());
            return list;
        }
        Log.d("Fetching check->Stop<", "-");
        return list;
    }

    public ArrayList<CategoryMaster> getCategoryDressingData(JourneyPlan jcp) {
        Log.d("Fetchecklidata->Start<-", "-");
        ArrayList<CategoryMaster> list = new ArrayList<>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery(" select cm.Category_Id as Category_Id, cm.Category as Category, IFNULL(d.EXISTORNOT,'false') as EXISTORNOT, IFNULL(d.CATEGORY_IMAGE,'') as CATEGORY_IMAGE,IFNULL( d.REASON_ID,'0') as REASON_ID, IFNULL(cmap.Category_Planogram_Imageurl,'') as Category_Planogram_Imageurl from Category_Master cm  " +
                    " inner join Mapping_Category_Dressing cmap on cm.Category_Id=cmap.Category_Id " +
                    " left join " +
                    " (select * from CATEGORY_DRESSING_DATA Where STORE_ID = " + jcp.getStoreId() + " and DATE ='" + jcp.getVisitDate() + "') as d on cm.Category_Id = d.CATEGORY_CD " +
                    " where state_id = " + jcp.getStateId() + " and Distributor_id = " + jcp.getDistributorId() + " and Store_Type_Id = " + jcp.getStoreTypeId() + " order by Category_Sequence", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    CategoryMaster ch = new CategoryMaster();
                    ch.setCategoryId(dbcursor.getInt(dbcursor.getColumnIndexOrThrow("Category_Id")));
                    ch.setCategory(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Category")));
                    ch.setExist(Boolean.parseBoolean(dbcursor.getString(dbcursor.getColumnIndexOrThrow("EXISTORNOT"))));
                    ch.setImage(dbcursor.getString(dbcursor.getColumnIndexOrThrow("CATEGORY_IMAGE")));
                    ch.setCategoryPlanogramImageurl(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Category_Planogram_Imageurl")));
                    ch.setReasonId(Integer.parseInt(dbcursor.getString(dbcursor.getColumnIndexOrThrow("REASON_ID"))));
                    list.add(ch);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {
            Log.d("Exc get windows list!", e.toString());
            return list;
        }
        Log.d("Fetching check->Stop<", "-");
        return list;
    }


    public ArrayList<CategoryMaster> getCategoryDBSRData(JourneyPlan jcp) {
        Log.d("Fetchecklidata->Start<-", "-");
        ArrayList<CategoryMaster> list = new ArrayList<>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("Select cm.Category_Id,cm.Category,IFNULL(cs.EXISTORNOT,'false') as EXISTORNOT,IFNULL(cs.CATEGORY_IMAGE,'') as CATEGORY_IMAGE from Category_Master cm " +
                    " left join (Select * from CATEGORY_DBSR_DATA) as cs on cm.Category_Id = cs.CATEGORY_CD" +
                    " and cs.STORE_ID = " + jcp.getStoreId() + " and cs.DATE = '" + jcp.getVisitDate() + "' order by cm.Category_Sequence", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    CategoryMaster ch = new CategoryMaster();
                    ch.setCategoryId(dbcursor.getInt(dbcursor.getColumnIndexOrThrow("Category_Id")));
                    ch.setCategory(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Category")));
                    ch.setExist(Boolean.parseBoolean(dbcursor.getString(dbcursor.getColumnIndexOrThrow("EXISTORNOT"))));
                    ch.setImage(dbcursor.getString(dbcursor.getColumnIndexOrThrow("CATEGORY_IMAGE")));
                    list.add(ch);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {
            Log.d("Exc get windows list!", e.toString());
            return list;
        }
        Log.d("Fetching check->Stop<", "-");
        return list;
    }


    public ArrayList<ChecklistGetterSetter> getChecklistData(String window_cd, JourneyPlan journeyPlan) {
        Log.d("Fetchecklidata->Start<-", "-");
        ArrayList<ChecklistGetterSetter> list = new ArrayList<>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT DISTINCT WC.Checklist_Id as CHECKLIST_CD, WC.Checklist as CHECKLIST,d.ANSWER_CD as ANSWER_CD  FROM  MAPPING_WINDOW_CHECKLIST MWC " +
                    " INNER JOIN WINDOW_CHECKLIST WC ON MWC.Checklist_Id = WC.Checklist_Id " +
                    "  left join " +
                    "  (select * from CheckList_DATA Where STORE_ID = " + journeyPlan.getStoreId() + " and DATE ='" + journeyPlan.getVisitDate() + "') as d " +
                    "  on WC.Checklist_Id = d.CHECKLIST_CD and MWC.Window_id = d.WINDOW_CD " +
                    " WHERE MWC.window_id ='" + window_cd + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    ChecklistGetterSetter ch = new ChecklistGetterSetter();
                    ch.setChecklist_cd(dbcursor.getString(dbcursor.getColumnIndexOrThrow("CHECKLIST_CD")));
                    ch.setChecklist(dbcursor.getString(dbcursor.getColumnIndexOrThrow("CHECKLIST")));
                    ch.setANSWER_CD(dbcursor.getString(dbcursor.getColumnIndexOrThrow("ANSWER_CD")));
                    //ch.setAnswer("");
                    list.add(ch);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {
            Log.d("Exc get windows list!", e.toString());
            return list;
        }
        Log.d("Fetching check->Stop<", "-");
        return list;
    }


    public ArrayList<ChecklistGetterSetter> getCategoryDressingChecklistData(String categoryId, JourneyPlan journeyPlan) {
        Log.d("Fetchecklidata->Start<-", "-");
        ArrayList<ChecklistGetterSetter> list = new ArrayList<>();
        Cursor dbcursor = null;
        try {

            dbcursor = db.rawQuery("SELECT DISTINCT WC.Checklist_Id as CHECKLIST_CD, WC.Checklist as CHECKLIST,d.ANSWER_CD as ANSWER_CD" +
                    " FROM Mapping_Category_Checklist MWC INNER JOIN WINDOW_CHECKLIST WC ON MWC.Checklist_Id = WC.Checklist_Id" +
                    "  left join" +
                    "  (select * from CATEGORY_DRESSING_CHECKLIST_DATA Where STORE_ID = " + journeyPlan.getStoreId() + " and DATE ='" + journeyPlan.getVisitDate() + "') as d " +
                    "  on WC.Checklist_Id = d.CHECKLIST_CD and MWC.Category_Id = d.CATEGORY_CD " +
                    " WHERE MWC.Category_Id ='" + categoryId + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    ChecklistGetterSetter ch = new ChecklistGetterSetter();
                    ch.setChecklist_cd(dbcursor.getString(dbcursor.getColumnIndexOrThrow("CHECKLIST_CD")));
                    ch.setChecklist(dbcursor.getString(dbcursor.getColumnIndexOrThrow("CHECKLIST")));
                    ch.setANSWER_CD(dbcursor.getString(dbcursor.getColumnIndexOrThrow("ANSWER_CD")));
                    //ch.setAnswer_type(dbcursor.getString(dbcursor.getColumnIndexOrThrow("ANSWER_TYPE")));
                    ch.setAnswer("");
                    list.add(ch);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {
            Log.d("Exc get windows list!", e.toString());
            return list;
        }
        Log.d("Fetching check->Stop<", "-");
        return list;
    }


    public ArrayList<AnswerChecklistGetterSetter> getChecklistAnswerData(String checklist_cd) {
        Log.d("Fetanswerdata->Start<-", "-");
        ArrayList<AnswerChecklistGetterSetter> list = new ArrayList<>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT DISTINCT Checklist_Id, Answer_Id, Answer FROM WINDOW_CHECKLIST_ANSWER  WHERE Checklist_Id =" + checklist_cd + "", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    AnswerChecklistGetterSetter ch = new AnswerChecklistGetterSetter();
                    ch.setChecklist_cd(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Checklist_Id")));
                    ch.setAnswer_cd(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Answer_Id")));
                    ch.setAnswer(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Answer")));
                    list.add(ch);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {
            Log.d("Exc get ans list!", e.toString());
            return list;
        }
        Log.d("Fet checklist->Stop<", "-");
        return list;
    }


    public long InsertWindowsData(String username, JourneyPlan jcp, ArrayList<WindowMaster> listDataHeader, HashMap<WindowMaster, ArrayList<ChecklistGetterSetter>> listDataChild) {
        long l = 0, common_id = 0;
        db.delete(CommonString.TABLE_WINDOWS_DATA, "STORE_ID ='" + jcp.getStoreId() + "'", null);
        db.delete(CommonString.TABLE_INSERT_CHECKLIST_DATA, "STORE_ID ='" + jcp.getStoreId() + "'", null);
        ContentValues values;
        ContentValues value2 = new ContentValues();
        try {

            for (int i = 0; i < listDataHeader.size(); i++) {
                values = new ContentValues();
                values.put("DATE", jcp.getVisitDate());
                values.put(CommonString.KEY_USER_ID, username);
                values.put(CommonString.KEY_STORE_ID, jcp.getStoreId());
                values.put("WINDOW_CD", listDataHeader.get(i).getWindowId());
                values.put("BRAND_ID", listDataHeader.get(i).getBrand_Id());
                values.put("EXISTORNOT", String.valueOf(listDataHeader.get(i).isExist()));
                values.put("WINDOW_IMAGE", listDataHeader.get(i).getImage());
                values.put("WINDOW_IMAGE2", listDataHeader.get(i).getImage2());
                values.put("REASON_ID", listDataHeader.get(i).getReasonId());

                common_id = db.insert(CommonString.TABLE_WINDOWS_DATA, null, values);

                if (listDataHeader.get(i).isExist()) {
                    ArrayList<ChecklistGetterSetter> checklist = listDataChild.get(listDataHeader.get(i));
                    for (int j = 0; j < checklist.size(); j++) {
                        values = new ContentValues();
                        values.put("DATE", jcp.getVisitDate());
                        values.put(CommonString.KEY_USER_ID, username);
                        values.put(CommonString.KEY_STORE_ID, jcp.getStoreId());
                        values.put("WINDOW_CD", listDataHeader.get(i).getWindowId());
                        values.put(CommonString.KEY_COMMON_ID, String.valueOf(common_id));
                        values.put("CHECKLIST_CD", checklist.get(j).getChecklist_cd());
                        values.put("ANSWER_CD", checklist.get(j).getANSWER_CD());

                        l = db.insert(CommonString.TABLE_INSERT_CHECKLIST_DATA, null, values);
                    }
                }
            }

            if (common_id > 0) {
                return common_id;
            } else {
                return 0;
            }

        } catch (Exception ex) {

            return 0;
        }
    }

    public long InsertWindowsCheckListData(String username, JourneyPlan jcp, WindowMaster windowMaster, ArrayList<ChecklistGetterSetter> listDataChild) {
        long l = 0, common_id = 0;
        db.delete(CommonString.TABLE_WINDOWS_DATA, "STORE_ID ='" + jcp.getStoreId() + "' AND WINDOW_CD = " + windowMaster.getWindowId() + "", null);
        db.delete(CommonString.TABLE_INSERT_CHECKLIST_DATA, "STORE_ID ='" + jcp.getStoreId() + "' AND WINDOW_CD = " + windowMaster.getWindowId() + "", null);
        ContentValues values;
        ContentValues value2 = new ContentValues();
        try {


            values = new ContentValues();
            values.put("DATE", jcp.getVisitDate());
            values.put(CommonString.KEY_USER_ID, username);
            values.put(CommonString.KEY_STORE_ID, jcp.getStoreId());
            values.put("WINDOW_CD", windowMaster.getWindowId());
            values.put("BRAND_ID", windowMaster.getBrand_Id());
            values.put("EXISTORNOT", String.valueOf(windowMaster.isExist()));
            values.put("WINDOW_IMAGE", windowMaster.getImage());
            values.put("WINDOW_IMAGE2", windowMaster.getImage2());
            values.put("REASON_ID", windowMaster.getReasonId());

            common_id = db.insert(CommonString.TABLE_WINDOWS_DATA, null, values);

            if (windowMaster.isExist()) {
                ArrayList<ChecklistGetterSetter> checklist = listDataChild;
                for (int j = 0; j < checklist.size(); j++) {
                    values = new ContentValues();
                    values.put("DATE", jcp.getVisitDate());
                    values.put(CommonString.KEY_USER_ID, username);
                    values.put(CommonString.KEY_STORE_ID, jcp.getStoreId());
                    values.put("WINDOW_CD", windowMaster.getWindowId());
                    values.put(CommonString.KEY_COMMON_ID, String.valueOf(common_id));
                    values.put("CHECKLIST_CD", checklist.get(j).getChecklist_cd());
                    values.put("ANSWER_CD", checklist.get(j).getANSWER_CD());

                    l = db.insert(CommonString.TABLE_INSERT_CHECKLIST_DATA, null, values);
                }
            }

            if (common_id > 0) {
                return common_id;
            } else {
                return 0;
            }

        } catch (Exception ex) {

            return 0;
        }
    }

    public long InsertCategoryDressingData(String username, JourneyPlan jcp, ArrayList<CategoryMaster> listDataHeader, HashMap<CategoryMaster, ArrayList<ChecklistGetterSetter>> listDataChild) {
        long l = 0, common_id = 0;
        db.delete(CommonString.TABLE_CATEGORY_DRESSING_DATA, "STORE_ID ='" + jcp.getStoreId() + "'", null);
        db.delete(CommonString.TABLE_INSERT_CATEGORY_DRESSING_CHECKLIST_DATA, "STORE_ID ='" + jcp.getStoreId() + "'", null);
        ContentValues values;
        ContentValues value2 = new ContentValues();
        try {

            for (int i = 0; i < listDataHeader.size(); i++) {
                values = new ContentValues();
                values.put("DATE", jcp.getVisitDate());
                values.put(CommonString.KEY_USER_ID, username);
                values.put(CommonString.KEY_STORE_ID, jcp.getStoreId());
                values.put("CATEGORY_CD", listDataHeader.get(i).getCategoryId());
                values.put("EXISTORNOT", String.valueOf(listDataHeader.get(i).isExist()));
                values.put("CATEGORY_IMAGE", listDataHeader.get(i).getImage());
                values.put("REASON_ID", listDataHeader.get(i).getReasonId());

                common_id = db.insert(CommonString.TABLE_CATEGORY_DRESSING_DATA, null, values);

                if (listDataHeader.get(i).isExist()) {
                    ArrayList<ChecklistGetterSetter> checklist = listDataChild.get(listDataHeader.get(i));
                    for (int j = 0; j < checklist.size(); j++) {
                        values = new ContentValues();
                        values.put("DATE", jcp.getVisitDate());
                        values.put(CommonString.KEY_USER_ID, username);
                        values.put(CommonString.KEY_STORE_ID, jcp.getStoreId());
                        values.put("CATEGORY_CD", listDataHeader.get(i).getCategoryId());
                        values.put(CommonString.KEY_COMMON_ID, String.valueOf(common_id));
                        values.put("CHECKLIST_CD", checklist.get(j).getChecklist_cd());
                        values.put("ANSWER_CD", checklist.get(j).getANSWER_CD());

                        l = db.insert(CommonString.TABLE_INSERT_CATEGORY_DRESSING_CHECKLIST_DATA, null, values);
                    }
                }

            }

            if (common_id > 0) {
                return common_id;
            } else {
                return 0;
            }

        } catch (Exception ex) {

            return 0;
        }

    }


    public long InsertCategoryDBSRData(String username, JourneyPlan jcp, ArrayList<CategoryMaster> listDataHeader) {
        long l = 0, common_id = 0;
        db.delete(CommonString.TABLE_CATEGORY_DBSR_DATA, "STORE_ID ='" + jcp.getStoreId() + "'", null);
        ContentValues values;
        try {

            for (int i = 0; i < listDataHeader.size(); i++) {
                values = new ContentValues();
                values.put("DATE", jcp.getVisitDate());
                values.put(CommonString.KEY_USER_ID, username);
                values.put(CommonString.KEY_STORE_ID, jcp.getStoreId());
                values.put("CATEGORY_CD", listDataHeader.get(i).getCategoryId());
                values.put("EXISTORNOT", String.valueOf(listDataHeader.get(i).isExist()));
                values.put("CATEGORY_IMAGE", listDataHeader.get(i).getImage());

                common_id = db.insert(CommonString.TABLE_CATEGORY_DBSR_DATA, null, values);
            }

            if (common_id > 0) {
                return common_id;
            } else {
                return 0;
            }
        } catch (Exception ex) {

            return 0;
        }

    }


    public ArrayList<WindowMaster> getWindowData(String storeid, String visit_date) {

        ArrayList<WindowMaster> list = new ArrayList<>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("select * From " + CommonString.TABLE_WINDOWS_DATA + " where " + CommonString.KEY_STORE_ID + " = " + storeid + " and " + CommonString.KEY_DDATE + " = '" + visit_date + "'", null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {

                    WindowMaster windowMaster = new WindowMaster();
                    windowMaster.setKey_Id(String.valueOf(dbcursor.getInt(dbcursor.getColumnIndexOrThrow(CommonString.KEY_IID))));
                    windowMaster.setBrand_Id(String.valueOf(dbcursor.getInt(dbcursor.getColumnIndexOrThrow(CommonString.KEY_BRAND_ID))));
                    windowMaster.setWindowId(Integer.valueOf(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_WINDOW_CD))));
                    windowMaster.setExist(Boolean.parseBoolean(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_EXISTORNOT))));
                    windowMaster.setImage(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_WINDOW_IMAGE)));
                    windowMaster.setImage2(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_WINDOW_IMAGE2)));
                    windowMaster.setReasonId(Integer.parseInt(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_REASON_ID))));

                    list.add(windowMaster);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {

            return list;
        }
        return list;
    }


    public ArrayList<CategoryMaster> getCategoryDressingData(String storeid, String visit_date) {

        ArrayList<CategoryMaster> list = new ArrayList<>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("select * From " + CommonString.TABLE_CATEGORY_DRESSING_DATA + " where " + CommonString.KEY_STORE_ID + " = " + storeid + " and " + CommonString.KEY_DDATE + " = '" + visit_date + "'", null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {

                    CategoryMaster categoryMaster = new CategoryMaster();
                    categoryMaster.setKey_Id(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_IID)));
                    categoryMaster.setCategoryId(Integer.valueOf(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_CATEGORY_CD))));
                    categoryMaster.setExist(Boolean.parseBoolean(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_EXISTORNOT))));
                    categoryMaster.setImage(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_CATEGORY_IMAGE)));
                    categoryMaster.setReasonId(Integer.parseInt(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_REASON_ID))));

                    list.add(categoryMaster);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {

            return list;
        }
        return list;
    }

    public long updateCheckoutStatus(String id, String status, String table) {
        ContentValues values = new ContentValues();
        try {
            values.put("Upload_Status", status);
            return db.update(table, values, "Store_Id " + " = '" + id + "'", null);
        } catch (Exception ex) {
            Log.e("Exception", " Journey_Plan" + ex.toString());
            return 0;
        }
    }

    public void updateStoreStatus(String storeid, String visitdate, String status) {
        try {
            ContentValues values = new ContentValues();
            values.put("Upload_Status", status);
            db.update("Journey_Plan", values, "Store_Id ='" + storeid + "' AND Visit_Date ='" + visitdate + "'", null);
        } catch (Exception e) {

        }
    }

    public long InsertCoverageData(CoverageBean data) {
        db.delete(CommonString.TABLE_COVERAGE_DATA, "STORE_ID" + "='" + data.getStoreId() + "' AND VISIT_DATE='" + data.getVisitDate() + "'", null);
        ContentValues values = new ContentValues();
        long l = 0;
        try {
            values.put(CommonString.KEY_STORE_ID, data.getStoreId());
            values.put(CommonString.KEY_USER_ID, data.getUserId());
            values.put(CommonString.KEY_VISIT_DATE, data.getVisitDate());
            values.put(CommonString.KEY_LATITUDE, data.getLatitude());
            values.put(CommonString.KEY_LONGITUDE, data.getLongitude());
            values.put(CommonString.KEY_IMAGE, data.getImage());
            values.put(CommonString.KEY_COVERAGE_REMARK, data.getRemark());
            values.put(CommonString.KEY_REASON_ID, data.getReasonid());
            values.put(CommonString.KEY_REASON, data.getReason());
            values.put(CommonString.KEY_CHECKOUT_IMAGE, data.getCkeckout_image());
            l = db.insert(CommonString.TABLE_COVERAGE_DATA, null, values);
        } catch (Exception ex) {

        }
        return l;
    }

/*
    public long updateStoreStatusOnLeave(String storeid, String visitdate, String status) {
        long id = 0;
        try {
            ContentValues values = new ContentValues();
            values.put("Upload_Status", status);
            id = db.update(CommonString.TABLE_Journey_Plan, values, "Strore_Id" + "='" + storeid + "' AND " + CommonString.KEY_VISIT_DATE + "='" + visitdate + "'", null);
            return id;
        } catch (Exception e) {
            return 0;
        }
    }
*/
   public long updateStoreStatusOnLeave(String storeid, String visitdate, String status) {
       long id = 0;
       try {
           ContentValues values = new ContentValues();
           values.put("UPLOAD_STATUS", status);
           id = db.update(CommonString.TABLE_Journey_Plan, values, CommonString.KEY_STORE_ID + "='" + storeid + "' AND "
                   + CommonString.KEY_VISIT_DATE + "='" + visitdate
                   + "'", null);
           return id;
       } catch (Exception e) {
           return 0;
       }
   }





    public boolean insertJCPData(JCPGetterSetter data) {
        db.delete("Journey_Plan", null, null);
        List<JourneyPlan> jcpList = data.getJourneyPlan();
        ContentValues values = new ContentValues();
        try {
            if (jcpList.size() == 0) {
                return false;
            }
            for (int i = 0; i < jcpList.size(); i++) {
                values.put("Store_Id", jcpList.get(i).getStoreId());
                values.put("Visit_Date", jcpList.get(i).getVisitDate());
                values.put("Distributor", jcpList.get(i).getDistributorN());
                values.put("Store_Name", jcpList.get(i).getStoreName());
                values.put("Address1", jcpList.get(i).getAddress1());
                values.put("Address2", jcpList.get(i).getAddress2());
                values.put("Landmark", jcpList.get(i).getLandmark());
                values.put("Pincode", jcpList.get(i).getPincode());
                values.put("Contact_Person", jcpList.get(i).getContactPerson());
                values.put("Contact_No", jcpList.get(i).getContactNo());
                values.put("City", jcpList.get(i).getCity());
                values.put("Store_Type", jcpList.get(i).getStoreType());
                values.put("Store_Category", jcpList.get(i).getStoreCategory());
                values.put("State_Id", jcpList.get(i).getStateId());
                values.put("Store_Type_Id", jcpList.get(i).getStoreTypeId());
                values.put("Store_Category_Id", jcpList.get(i).getStoreCategoryId());
                values.put("Reason_Id", jcpList.get(i).getReasonId());
                values.put("Upload_Status", jcpList.get(i).getUploadStatus());
                values.put("Geo_Tag", jcpList.get(i).getGeoTag());
                values.put("Distributor_Id", jcpList.get(i).getDistributorId());
                values.put("Classification_Id", jcpList.get(i).getClassificationId());
                values.put("GeoFencing", jcpList.get(i).getGeoFencing());
                values.put("Latitude", jcpList.get(i).getLatitude());
                values.put("Longitude", jcpList.get(i).getLongitude());
                values.put("Store_Code", jcpList.get(i).getStore_Code());
                values.put("Classification", jcpList.get(i).getClassification());

                long id = db.insert("Journey_Plan", null, values);
                if (id == -1) {
                    throw new Exception();
                }
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.d("Exception in Jcp", ex.toString());
            return false;
        }
    }



    public boolean insertJCP_DBSRSavedData(JourneyPlan data) {
        db.delete(CommonString.TABLE_Journey_Plan_DBSR_Saved, "Store_Id = " + data.getStoreId() + "", null);
        ContentValues values = new ContentValues();
        try {
            if (data == null) {
                return false;
            }
            values.put("Store_Id", data.getStoreId());
            values.put("Visit_Date", data.getVisitDate());
            values.put("Distributor", data.getDistributorN());
            values.put("Store_Name", data.getStoreName());
            values.put("Address1", data.getAddress1());
            values.put("Address2", data.getAddress2());
            values.put("Landmark", data.getLandmark());
            values.put("Pincode", data.getPincode());
            values.put("Contact_Person", data.getContactPerson());
            values.put("Contact_No", data.getContactNo());
            values.put("City", data.getCity());
            values.put("Store_Type", data.getStoreType());
            values.put("Store_Category", data.getStoreCategory());
            values.put("State_Id", data.getStateId());
            values.put("Store_Type_Id", data.getStoreTypeId());
            values.put("Store_Category_Id", data.getStoreCategoryId());
            values.put("Reason_Id", data.getReasonId());
            values.put("Upload_Status", data.getUploadStatus());
            values.put("Geo_Tag", data.getGeoTag());
            values.put("Distributor_Id", data.getDistributorId());
            long id = db.insert(CommonString.TABLE_Journey_Plan_DBSR_Saved, null, values);
            if (id == -1) {
                throw new Exception();
            }

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();

            return false;
        }
    }

    public boolean insertBrandMasterData(BrandMasterGetterSetter BrandMastergetset) {
        db.delete("Brand_Master", null, null);
        ContentValues values = new ContentValues();
        List<BrandMaster> data = BrandMastergetset.getBrandMaster();
        try {
            if (data.size() == 0) {
                return false;
            }

            for (int i = 0; i < data.size(); i++) {

                values.put("Brand", data.get(i).getBrand());
                values.put("Brand_Id", data.get(i).getBrandId());
                values.put("Brand_Sequence", data.get(i).getBrandSequence());
                values.put("Sub_Category_Id", data.get(i).getSubcategoryId());
                values.put("Company_Id", data.get(i).getCompany_Id());

                long id = db.insert("Brand_Master", null, values);
                if (id == -1) {
                    throw new Exception();
                }
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.d("Database Exception  ", ex.toString());
            return false;
        }
    }


    public boolean insertStoreTypeMasterData(StoreTypeMasterGetterSetter storeTypeMaster) {
        db.delete("Store_Type_Master", null, null);
        ContentValues values = new ContentValues();
        List<StoreTypeMaster> data = storeTypeMaster.getStoreTypeMaster();
        try {
            if (data.size() == 0) {
                return false;
            }

            for (int i = 0; i < data.size(); i++) {

                values.put("Store_Type", data.get(i).getStoreType());
                values.put("Store_Type_Id", data.get(i).getStoreTypeId());
                values.put("Show_Reference_Image", data.get(i).getShowReferenceImage());

                long id = db.insert("Store_Type_Master", null, values);
                if (id == -1) {
                    throw new Exception();
                }
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.d("Database Exception  ", ex.toString());
            return false;
        }
    }


    public boolean insertNonWorkingData(NonWorkingReasonGetterSetter nonWorkingdata) {
        db.delete("Non_Working_Reason", null, null);
        ContentValues values = new ContentValues();
        List<NonWorkingReason> data = nonWorkingdata.getNonWorkingReason();
        try {
            if (data.size() == 0) {
                return false;
            }

            for (int i = 0; i < data.size(); i++) {

                values.put("Reason_Id", data.get(i).getReasonId());
                values.put("Reason", data.get(i).getReason());
                values.put("Entry_Allow", data.get(i).getEntryAllow());
                values.put("Image_Allow", data.get(i).getImageAllow());
                values.put("GPS_Mandatory", data.get(i).getGPSMandatory());

                long id = db.insert("Non_Working_Reason", null, values);
                if (id == -1) {
                    throw new Exception();
                }
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.d("Database Exception  ", ex.toString());
            return false;
        }
    }

    public boolean insertNonCategoryReasonData(NonCategoryReasonGetterSetter nonCategorydata) {
        db.delete("Non_Category_Reason", null, null);
        ContentValues values = new ContentValues();
        List<NonCategoryReason> data = nonCategorydata.getNonCategoryReason();
        try {
            if (data.size() == 0) {
                return false;
            }

            for (int i = 0; i < data.size(); i++) {
                values.put("CReason_Id", data.get(i).getCReasonId());
                values.put("CReason", data.get(i).getCReason());
                long id = db.insert("Non_Category_Reason", null, values);
                if (id == -1) {
                    throw new Exception();
                }
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.d("Database Exception  ", ex.toString());
            return false;
        }
    }

    public boolean insertCategoryMasterData(CategoryMasterGetterSetter CategoryMaster) {
        db.delete("Category_Master", null, null);
        ContentValues values = new ContentValues();
        List<CategoryMaster> data = CategoryMaster.getCategoryMaster();
        try {
            if (data.size() == 0) {
                return false;
            }
            for (int i = 0; i < data.size(); i++) {
                values.put("Category", data.get(i).getCategory());
                values.put("Category_Id", data.get(i).getCategoryId());
                values.put("Category_Sequence", data.get(i).getCategorySequence());
                long id = db.insert("Category_Master", null, values);
                if (id == -1) {
                    throw new Exception();
                }
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.d("Database Exception  ", ex.toString());
            return false;
        }
    }

    public boolean insertMappingCategoryChecklistdata(MappingCategoryChecklistGetterSetter infotype) {
        db.delete("Mapping_Category_Checklist", null, null);
        ContentValues values = new ContentValues();
        List<MappingCategoryChecklist> data = infotype.getMappingCategoryChecklist();
        try {
            if (data.size() == 0) {
                return false;
            }
            for (int i = 0; i < data.size(); i++) {
                values.put("Category_Id", data.get(i).getCategoryId());
                values.put("Checklist_Id", data.get(i).getChecklistId());

                long id = db.insert("Mapping_Category_Checklist", null, values);
                if (id == -1) {
                    throw new Exception();
                }
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.d("Database Exception  ", ex.toString());
            return false;
        }
    }


    public boolean insertWindowChecklistData(WindowChecklistGetterSetter BrandMaster) {
        db.delete("Window_Checklist", null, null);
        ContentValues values = new ContentValues();
        List<WindowChecklist> data = BrandMaster.getWindowChecklist();
        try {
            if (data.size() == 0) {
                return false;
            }

            for (int i = 0; i < data.size(); i++) {

                values.put("Checklist_Id", data.get(i).getChecklistId());
                values.put("Checklist", data.get(i).getChecklist());

                long id = db.insert("Window_Checklist", null, values);
                if (id == -1) {
                    throw new Exception();
                }
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.d("Database Exception  ", ex.toString());
            return false;
        }
    }

    public boolean insertmappingInitiativedata(MappingInitiativeGetterSetter infotype) {
        db.delete("Mapping_Visibility_Initiatives_New", null, null);

        ContentValues values = new ContentValues();
        List<MappingVisibilityInitiative> data = infotype.getMappingVisibilityInitiatives();
        try {
            if (data.size() == 0) {
                return false;
            }

            for (int i = 0; i < data.size(); i++) {
                values.put("State_Id", data.get(i).getStateId());
                values.put("Distributor_Id", data.get(i).getDistributorId());
                values.put("Store_Type_Id", data.get(i).getStoreTypeId());
                values.put("Brand_id", data.get(i).getBrandId());
                values.put("Window_Id", data.get(i).getWindowId());
                values.put("Classification_Id", data.get(i).getClassificationId());

                values.put("Store_Category_Id", data.get(i).getStoreCategoryId());

                long id = db.insert("Mapping_Visibility_Initiatives_New", null, values);
                if (id == -1) {
                    throw new Exception();
                }
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.d("Database Exception  ", ex.toString());
            return false;
        }
    }

    public boolean insertwindowChecklistAnsdata(WindowCheckAnswerGetterSetter infotype) {
        db.delete("Window_Checklist_Answer", null, null);
        ContentValues values = new ContentValues();
        List<WindowChecklistAnswer> data = infotype.getWindowChecklistAnswer();
        try {
            if (data.size() == 0) {
                return false;
            }

            for (int i = 0; i < data.size(); i++) {
                values.put("Answer_Id", data.get(i).getAnswerId());
                values.put("Answer", data.get(i).getAnswer());
                values.put("Checklist_Id", data.get(i).getChecklistId());
                long id = db.insert("Window_Checklist_Answer", null, values);
                if (id == -1) {
                    throw new Exception();
                }
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.d("Database Exception  ", ex.toString());
            return false;
        }
    }


    public boolean insertNonWindowReasonData(NonWindowReasonGetterSetter infotype) {
        db.delete("Non_Window_Reason", null, null);
        ContentValues values = new ContentValues();
        List<NonWindowReason> data = infotype.getNonWindowReason();
        try {
            if (data.size() == 0) {
                return false;
            }

            for (int i = 0; i < data.size(); i++) {
                values.put("WReason_Id", data.get(i).getWReasonId());
                values.put("WReason", data.get(i).getWReason());
                long id = db.insert("Non_Window_Reason", null, values);
                if (id == -1) {
                    throw new Exception();
                }
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.d("Database Exception  ", ex.toString());
            return false;
        }
    }

    public boolean isCoverageDataFilled(String visit_date) {
        boolean filled = false;
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT * FROM COVERAGE_DATA " + "where " + CommonString.KEY_VISIT_DATE + "<>'" + visit_date + "'", null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                int icount = dbcursor.getCount();
                dbcursor.close();
                if (icount > 0) {
                    filled = true;
                } else {
                    filled = false;
                }

            }

        } catch (Exception e) {
            return filled;
        }

        return filled;
    }

    public void deletePreviousJouneyPlanDBSRData(String visit_date) {
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT  * from Journey_Plan_DBSR_Saved where Visit_Date <> '" + visit_date + "'", null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                int icount = dbcursor.getCount();
                dbcursor.close();
                if (icount > 0) {
                    db.delete(CommonString.TABLE_Journey_Plan_DBSR_Saved, null, null);
                }
                dbcursor.close();
            }

        } catch (Exception e) {

        }
    }

    public JourneyPlan getSpecificStoreDataPrevious(String date, String store_id) {
        JourneyPlan sb = new JourneyPlan();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT * from Journey_Plan  " +
                    "where Visit_Date <> '" + date + "' AND Store_Id='" + store_id + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    sb.setStoreId(Integer.parseInt(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Store_Id"))));
                    sb.setVisitDate((dbcursor.getString(dbcursor.getColumnIndexOrThrow("Visit_Date"))));
                    sb.setStoreName(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Store_Name")));
                    sb.setAddress1(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Address1")));
                    sb.setAddress2((dbcursor.getString(dbcursor.getColumnIndexOrThrow("Address2"))));
                    sb.setLandmark(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Landmark")));
                    sb.setPincode(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Pincode")));
                    sb.setContactPerson(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Contact_Person")));
                    sb.setContactNo(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Contact_No")));
                    sb.setCity(dbcursor.getString(dbcursor.getColumnIndexOrThrow("City")));
                    sb.setStoreType(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Store_Type")));
                    sb.setStoreCategory(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Store_Category")));
                    sb.setStateId(dbcursor.getString(dbcursor.getColumnIndexOrThrow("State_Id")));
                    sb.setStoreTypeId(Integer.parseInt(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Store_Type_Id"))));
                    sb.setStoreCategoryId(Integer.parseInt(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Store_Category_Id"))));
                    sb.setReasonId(Integer.parseInt(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Reason_Id"))));
                    sb.setUploadStatus(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Upload_Status")));
                    sb.setGeoTag(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Geo_Tag")));
                    sb.setDistributorId(Integer.parseInt(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Distributor_Id"))));
                    sb.setDistributorN(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Distributor")));
                    sb.setClassification(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Classification")));
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return sb;
            }

        } catch (Exception e) {
            Log.d("Exception get JCP!", e.toString());
            return sb;
        }

        return sb;
    }

    public ArrayList<CoverageBean> getSpecificCoverageData(String visitdate, String store_cd) {
        ArrayList<CoverageBean> list = new ArrayList<CoverageBean>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT  * from " + CommonString.TABLE_COVERAGE_DATA + " where " + CommonString.KEY_VISIT_DATE + "='" + visitdate + "' AND " +
                    CommonString.KEY_STORE_ID + "='" + store_cd + "'", null);

            if (dbcursor != null) {

                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    CoverageBean sb = new CoverageBean();
                    sb.setStoreId(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
                    sb.setUserId(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_USER_ID)));
                    sb.setVisitDate(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_VISIT_DATE)));
                    sb.setLatitude(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_LATITUDE)));
                    sb.setLongitude(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_LONGITUDE)));
                    sb.setImage(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_IMAGE)));
                    sb.setReason(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_REASON)));
                    sb.setReasonid(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_REASON_ID)));
                    sb.setMID(Integer.parseInt(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_ID))));
                    sb.setCkeckout_image(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_COVERAGE_REMARK)));

                    sb.setRemark(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_COVERAGE_REMARK)));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {

        }
        return list;
    }


    public ArrayList<JourneyPlan> getSpecificStoreData(String store_cd) {
        ArrayList<JourneyPlan> list = new ArrayList<JourneyPlan>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT  * from Journey_Plan  " + "where Store_Id ='" + store_cd + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    JourneyPlan sb = new JourneyPlan();
                    sb.setStoreId(Integer.parseInt(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Store_Id"))));
                    sb.setVisitDate((dbcursor.getString(dbcursor.getColumnIndexOrThrow("Visit_Date"))));
                    sb.setStoreName(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Store_Name")));
                    sb.setAddress1(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Address1")));
                    sb.setAddress2((dbcursor.getString(dbcursor.getColumnIndexOrThrow("Address2"))));
                    sb.setLandmark(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Landmark")));
                    sb.setPincode(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Pincode")));
                    sb.setContactPerson(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Contact_Person")));
                    sb.setContactNo(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Contact_No")));
                    sb.setCity(dbcursor.getString(dbcursor.getColumnIndexOrThrow("City")));
                    sb.setStoreType(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Store_Type")));
                    sb.setStoreCategory(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Store_Category")));
                    sb.setStateId(dbcursor.getString(dbcursor.getColumnIndexOrThrow("State_Id")));
                    sb.setStoreTypeId(Integer.parseInt(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Store_Type_Id"))));
                    sb.setStoreCategoryId(Integer.parseInt(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Store_Category_Id"))));
                    sb.setReasonId(Integer.parseInt(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Reason_Id"))));
                    sb.setUploadStatus(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Upload_Status")));
                    sb.setGeoTag(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Geo_Tag")));
                    sb.setDistributorId(Integer.parseInt(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Distributor_Id"))));
                    sb.setDistributorN(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Distributor")));
                    sb.setStore_Code(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Store_Code")));

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception get JCP!", e.toString());
            return list;
        }


        return list;
    }


    public ArrayList<JourneyPlan> getSpecificStore_DBSRSavedData(String store_cd) {
        ArrayList<JourneyPlan> list = new ArrayList<JourneyPlan>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT  * from Journey_Plan_DBSR_Saved  " + "where Store_Id ='" + store_cd + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    JourneyPlan sb = new JourneyPlan();
                    sb.setStoreId(Integer.parseInt(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Store_Id"))));
                    sb.setVisitDate((dbcursor.getString(dbcursor.getColumnIndexOrThrow("Visit_Date"))));
                    sb.setStoreName(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Store_Name")));
                    sb.setAddress1(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Address1")));
                    sb.setAddress2((dbcursor.getString(dbcursor.getColumnIndexOrThrow("Address2"))));
                    sb.setLandmark(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Landmark")));
                    sb.setPincode(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Pincode")));
                    sb.setContactPerson(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Contact_Person")));
                    sb.setContactNo(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Contact_No")));
                    sb.setCity(dbcursor.getString(dbcursor.getColumnIndexOrThrow("City")));
                    sb.setStoreType(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Store_Type")));
                    sb.setStoreCategory(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Store_Category")));
                    sb.setStateId(dbcursor.getString(dbcursor.getColumnIndexOrThrow("State_Id")));
                    sb.setStoreTypeId(Integer.parseInt(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Store_Type_Id"))));
                    sb.setStoreCategoryId(Integer.parseInt(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Store_Category_Id"))));
                    sb.setReasonId(Integer.parseInt(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Reason_Id"))));
                    sb.setUploadStatus(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Upload_Status")));
                    sb.setGeoTag(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Geo_Tag")));
                    sb.setDistributorId(Integer.parseInt(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Distributor_Id"))));
                    sb.setDistributorN(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Distributor")));

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception get JCP!", e.toString());
            return list;
        }


        return list;
    }


    public ArrayList<NonWorkingReason> getNonWorkingEntryAllowData() {

        ArrayList<NonWorkingReason> list = new ArrayList<NonWorkingReason>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT * FROM Non_Working_Reason WHERE Entry_Allow=1", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    NonWorkingReason sb = new NonWorkingReason();

                    sb.setReasonId(Integer.valueOf(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Reason_Id"))));
                    sb.setReason(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Reason")));
                    sb.setEntryAllow("1".equalsIgnoreCase(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Entry_Allow"))));
                    sb.setImageAllow("1".equalsIgnoreCase(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Image_Allow"))));
                    sb.setGPSMandatory("1".equalsIgnoreCase(dbcursor.getString(dbcursor.getColumnIndexOrThrow("GPS_Mandatory"))));

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            return list;
        }
        return list;
    }

    public ArrayList<NonWorkingReason> getNonWorkingData() {
        ArrayList<NonWorkingReason> list = new ArrayList<NonWorkingReason>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT * FROM Non_Working_Reason", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    NonWorkingReason sb = new NonWorkingReason();

                    sb.setReasonId(Integer.valueOf(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Reason_Id"))));
                    sb.setReason(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Reason")));
                    sb.setEntryAllow("1".equalsIgnoreCase(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Entry_Allow"))));
                    sb.setImageAllow("1".equalsIgnoreCase(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Image_Allow"))));
                    sb.setGPSMandatory("1".equalsIgnoreCase(dbcursor.getString(dbcursor.getColumnIndexOrThrow("GPS_Mandatory"))));

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {
            return list;
        }
        return list;
    }


    public ArrayList<GeotaggingBeans> getinsertGeotaggingData(String storeid, String status) {
        ArrayList<GeotaggingBeans> list = new ArrayList<>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("Select * from " + CommonString.TABLE_STORE_GEOTAGGING + "" +
                    " where " + CommonString.KEY_STORE_ID + " ='" + storeid + "' and " + CommonString.KEY_STATUS + " = '" + status + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    GeotaggingBeans geoTag = new GeotaggingBeans();
                    geoTag.setStoreid(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
                    geoTag.setLatitude(Double.parseDouble(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_LATITUDE))));
                    geoTag.setLongitude(Double.parseDouble(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_LONGITUDE))));
                    geoTag.setImage(dbcursor.getString(dbcursor.getColumnIndexOrThrow("FRONT_IMAGE")));
                    list.add(geoTag);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception Brands",
                    e.toString());
            return list;
        }
        return list;

    }


    public ArrayList<JourneyPlan> getStoreData(String date) {
        ArrayList<JourneyPlan> list = new ArrayList<JourneyPlan>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT  * FROM Journey_Plan  " + "WHERE Visit_Date ='" + date + "' ORDER BY Store_Name", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    JourneyPlan sb = new JourneyPlan();
                    sb.setStoreId(Integer.parseInt(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Store_Id"))));
                    sb.setVisitDate((dbcursor.getString(dbcursor.getColumnIndexOrThrow("Visit_Date"))));
                    sb.setStoreName(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Store_Name")));
                    sb.setAddress1(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Address1")));
                    sb.setAddress2((dbcursor.getString(dbcursor.getColumnIndexOrThrow("Address2"))));
                    sb.setLandmark(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Landmark")));
                    sb.setPincode(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Pincode")));
                    sb.setContactPerson(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Contact_Person")));
                    sb.setContactNo(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Contact_No")));
                    sb.setCity(dbcursor.getString(dbcursor.getColumnIndexOrThrow("City")));
                    sb.setStoreType(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Store_Type")));
                    sb.setStoreCategory(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Store_Category")));
                    sb.setStateId(dbcursor.getString(dbcursor.getColumnIndexOrThrow("State_Id")));
                    sb.setStoreTypeId(Integer.parseInt(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Store_Type_Id"))));
                    sb.setStoreCategoryId(Integer.parseInt(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Store_Category_Id"))));
                    sb.setReasonId(Integer.parseInt(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Reason_Id"))));
                    sb.setUploadStatus(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Upload_Status")));
                    sb.setGeoTag(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Geo_Tag")));
                    sb.setDistributorId(Integer.parseInt(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Distributor_Id"))));
                    sb.setDistributorN(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Distributor")));

                    sb.setGeoFencing(Integer.parseInt(dbcursor.getString(dbcursor.getColumnIndexOrThrow("GeoFencing"))));
                    sb.setLatitude(dbcursor.getDouble(dbcursor.getColumnIndexOrThrow("Latitude")));
                    sb.setLongitude(dbcursor.getDouble(dbcursor.getColumnIndexOrThrow("Longitude")));
                    sb.setStore_Code(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Store_Code")));
                    sb.setClassificationId(Integer.parseInt(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Classification_Id"))));
                    sb.setClassification(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Classification")));
                    if (sb.getStoreTypeId() == 1) {
                        sb.setColourCode(R.color.peachpuff);
                    } else if (sb.getStoreTypeId() == 2) {
                        sb.setColourCode(R.color.lightskyblue);
                    } else if (sb.getStoreTypeId() == 3) {
                        sb.setColourCode(R.color.gainsboro);
                    } else {
                        sb.setColourCode(R.color.lightsalmon);
                    }

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception get JCP!", e.toString());
            return list;
        }


        return list;
    }

    public ArrayList<JourneyPlan> getStoreData_DBSR(String date) {
        ArrayList<JourneyPlan> list = new ArrayList<JourneyPlan>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT  * FROM Journey_Plan_DBSR  " + "WHERE Visit_Date ='" + date + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    JourneyPlan sb = new JourneyPlan();
                    sb.setStoreId(Integer.parseInt(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Store_Id"))));
                    sb.setVisitDate((dbcursor.getString(dbcursor.getColumnIndexOrThrow("Visit_Date"))));
                    sb.setStoreName(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Store_Name")));
                    sb.setAddress1(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Address1")));
                    sb.setAddress2((dbcursor.getString(dbcursor.getColumnIndexOrThrow("Address2"))));
                    sb.setLandmark(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Landmark")));
                    sb.setPincode(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Pincode")));
                    sb.setContactPerson(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Contact_Person")));
                    sb.setContactNo(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Contact_No")));
                    sb.setCity(dbcursor.getString(dbcursor.getColumnIndexOrThrow("City")));
                    sb.setStoreType(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Store_Type")));
                    sb.setStoreCategory(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Store_Category")));
                    sb.setStateId(dbcursor.getString(dbcursor.getColumnIndexOrThrow("State_Id")));
                    sb.setStoreTypeId(Integer.parseInt(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Store_Type_Id"))));
                    sb.setStoreCategoryId(Integer.parseInt(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Store_Category_Id"))));
                    sb.setReasonId(Integer.parseInt(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Reason_Id"))));
                    sb.setUploadStatus(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Upload_Status")));
                    sb.setGeoTag(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Geo_Tag")));
                    sb.setDistributorId(Integer.parseInt(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Distributor_Id"))));
                    sb.setDistributorN(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Distributor")));
                    sb.setWeeklyUpload(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Weekly_Upload")));
                    sb.setClassificationId(Integer.parseInt(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Classification_Id"))));
                    if (sb.getStoreTypeId() == 1) {
                        sb.setColourCode(R.color.peachpuff);
                    } else if (sb.getStoreTypeId() == 2) {
                        sb.setColourCode(R.color.lightskyblue);
                    } else if (sb.getStoreTypeId() == 3) {
                        sb.setColourCode(R.color.gainsboro);
                    }
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {

            return list;
        }
        return list;
    }

    public ArrayList<JourneyPlan> getStoreData_DBSR_Saved(String date) {
        ArrayList<JourneyPlan> list = new ArrayList<JourneyPlan>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT  * FROM Journey_Plan_DBSR_Saved  " + "WHERE Visit_Date ='" + date + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    JourneyPlan sb = new JourneyPlan();
                    sb.setStoreId(Integer.parseInt(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Store_Id"))));
                    sb.setVisitDate((dbcursor.getString(dbcursor.getColumnIndexOrThrow("Visit_Date"))));
                    sb.setStoreName(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Store_Name")));
                    sb.setAddress1(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Address1")));
                    sb.setAddress2((dbcursor.getString(dbcursor.getColumnIndexOrThrow("Address2"))));
                    sb.setLandmark(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Landmark")));
                    sb.setPincode(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Pincode")));
                    sb.setContactPerson(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Contact_Person")));
                    sb.setContactNo(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Contact_No")));
                    sb.setCity(dbcursor.getString(dbcursor.getColumnIndexOrThrow("City")));
                    sb.setStoreType(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Store_Type")));
                    sb.setStoreCategory(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Store_Category")));
                    sb.setStateId(dbcursor.getString(dbcursor.getColumnIndexOrThrow("State_Id")));
                    sb.setStoreTypeId(Integer.parseInt(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Store_Type_Id"))));
                    sb.setStoreCategoryId(Integer.parseInt(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Store_Category_Id"))));
                    sb.setReasonId(Integer.parseInt(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Reason_Id"))));
                    sb.setUploadStatus(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Upload_Status")));
                    sb.setGeoTag(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Geo_Tag")));
                    sb.setDistributorId(Integer.parseInt(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Distributor_Id"))));
                    sb.setDistributorN(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Distributor")));
                    sb.setClassificationId(Integer.parseInt(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Classification_Id"))));
                    if (sb.getStoreTypeId() == 1) {
                        sb.setColourCode(R.color.peachpuff);
                    } else if (sb.getStoreTypeId() == 2) {
                        sb.setColourCode(R.color.lightskyblue);
                    } else if (sb.getStoreTypeId() == 3) {
                        sb.setColourCode(R.color.gainsboro);
                    }
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            return list;
        }
        return list;
    }


    public ArrayList<CoverageBean> getCoverageData(String visitdate) {
        ArrayList<CoverageBean> list = new ArrayList<CoverageBean>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT * FROM " + CommonString.TABLE_COVERAGE_DATA + " WHERE " + CommonString.KEY_VISIT_DATE + "='" + visitdate + "'", null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    CoverageBean sb = new CoverageBean();
                    sb.setStoreId(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
                    sb.setUserId(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_USER_ID)));
                    sb.setVisitDate(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_VISIT_DATE)));
                    sb.setLatitude(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_LATITUDE)));
                    sb.setLongitude(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_LONGITUDE)));
                    sb.setImage(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_IMAGE)));
                    sb.setReason(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_REASON)));
                    sb.setReasonid(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_REASON_ID)));
                    sb.setMID(Integer.parseInt(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_ID))));
                    sb.setCkeckout_image(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_CHECKOUT_IMAGE)));
                    sb.setRemark(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_COVERAGE_REMARK)));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {


        }

        return list;

    }


    public boolean isWindowDataFilled(int storeId, int window_cd) {
        boolean filled = false;
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT * FROM " + CommonString.TABLE_WINDOWS_DATA + " WHERE STORE_ID= '" + storeId + "' AND WINDOW_CD ='" + window_cd + "'", null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                int icount = dbcursor.getInt(0);
                dbcursor.close();
                if (icount > 0) {
                    filled = true;
                } else {
                    filled = false;
                }
            }
        } catch (Exception e) {
            return filled;
        }
        return filled;
    }


    public ArrayList<StoreTypeMaster> geVISITED_WITH_MASTERData() {
        ArrayList<StoreTypeMaster> list = new ArrayList<>();
        Cursor dbcursor = null;

        try {

            dbcursor = db.rawQuery("SELECT * FROM Store_Type_Master ", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    StoreTypeMaster sb = new StoreTypeMaster();

                    sb.setStoreType(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Store_Type")));
                    sb.setStoreTypeId(dbcursor.getInt(dbcursor.getColumnIndexOrThrow("Store_Type_Id")));
                    if (dbcursor.getString(dbcursor.getColumnIndexOrThrow("Show_Reference_Image")).equals(1)) {
                        sb.setShowReferenceImage(true);
                    } else {
                        sb.setShowReferenceImage(false);
                    }

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            return list;
        }

        return list;
    }

    public long insertStoreProfileData(JourneyPlan JCP, StoreProfileGetterSetter storeProfile) {
        db.delete(CommonString.TABLE_STORE_PROFILE_DATA, " STORE_ID" + "='" + JCP.getStateId() + "' AND VISIT_DATE='" + JCP.getVisitDate() + "'", null);
        ContentValues values = new ContentValues();
        long l = 0;
        try {
            db.beginTransaction();
            if (!JCP.getStoreName().isEmpty()) {
                values.put(CommonString.KEY_STORE_ID, JCP.getStoreId());
                values.put(CommonString.KEY_VISIT_DATE, JCP.getVisitDate());
                values.put(CommonString.KEY_STORE_TYPE, storeProfile.getStore_type());
                values.put(CommonString.KEY_STORE_TYPE_CD, storeProfile.getStore_type_cd());
                values.put(CommonString.KEY_STORE_NAME, JCP.getStoreName());
                values.put(CommonString.KEY_STORE_ADDRESS, JCP.getAddress1());
                values.put(CommonString.KEY_STORE_CITY, JCP.getCity());


                l = db.insert(CommonString.TABLE_STORE_PROFILE_DATA, null, values);
            }
            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (Exception ex) {
            Log.d("Database Exception", " while Insert Header Data " + ex.toString());
        }
        return l;
    }


    public StoreProfileGetterSetter getStoreProfileData(String store_cd, String visit_date) {
        StoreProfileGetterSetter sb = new StoreProfileGetterSetter();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT * FROM STORE_PROFILE_DATA WHERE STORE_ID ='" + store_cd + "' AND VISIT_DATE='" + visit_date + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    sb.setStore_type_cd((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE_TYPE_CD))));
                    sb.setStore_type(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE_TYPE)));
                    sb.setStoreCd(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
                    sb.setStore_visit_date(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_VISIT_DATE)));
                    sb.setStore_name(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE_NAME)));
                    sb.setStore_addres(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE_ADDRESS)));
                    sb.setStore_city(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE_CITY)));

                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return sb;
            }

        } catch (Exception e) {
            Log.d("Exception get JCP!", e.toString());
            return sb;
        }

        return sb;
    }

    //-----------------------------------------------------------------
    public boolean insertMenuMasterData(MenuMasterGetterSetter menuMasterGetterSetter) {
        db.delete("Menu_Master", null, null);
        ContentValues values = new ContentValues();
        List<MenuMaster> data = menuMasterGetterSetter.getMenuMaster();
        try {
            if (data.size() == 0) {
                return false;
            }

            for (int i = 0; i < data.size(); i++) {
                values.put("Menu_Id", data.get(i).getMenuId());
                values.put("Menu_Name", data.get(i).getMenuName());
                values.put("Normal_Icon", data.get(i).getNormalIcon());
                values.put("Tick_Icon", data.get(i).getTickIcon());
                values.put("Grey_Icon", data.get(i).getGreyIcon());
                values.put("Menu_Path", data.get(i).getMenuPath());
                values.put("Menu_Sequence", data.get(i).getMenuSequence());
                long id = db.insert("Menu_Master", null, values);
                if (id == -1) {
                    throw new Exception();
                }
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.d("Database Exception  ", ex.toString());
            return false;
        }
    }

    //Insert MenuMapping
    public boolean insertMappingMenuData(MappingMenuGetterSetter mappingMenuGetterSetter) {
        db.delete("Mapping_Menu", null, null);
        ContentValues values = new ContentValues();
        List<MappingMenu> data = mappingMenuGetterSetter.getMappingMenu();
        try {
            if (data.size() == 0) {
                return false;
            }

            for (int i = 0; i < data.size(); i++) {
                values.put("Store_Type_Id", data.get(i).getStoreTypeId());
                values.put("Menu_Id", data.get(i).getMenuId());
                values.put("Store_Category_Id", data.get(i).getStoreCategoryId());

                long id = db.insert("Mapping_Menu", null, values);
                if (id == -1) {
                    throw new Exception();
                }
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.d("Database Exception  ", ex.toString());
            return false;
        }
    }

    //get Menu List data for Entry Menu
    public ArrayList<MenuMaster> getMenuData(int Store_Type_Id, int store_category_id) {
        Log.d("Fetchecklidata->Start<-", "-");
        ArrayList<MenuMaster> list = new ArrayList<>();
        Cursor dbcursor = null;
        try {

            dbcursor = db.rawQuery("SELECT M.Menu_Id, M.Menu_Name, M.Normal_Icon, M.Tick_Icon, M.Grey_Icon, M.Menu_Path FROM Menu_Master M INNER JOIN Mapping_Menu MM ON M.Menu_Id = MM.Menu_Id WHERE MM.Store_Type_Id=" + Store_Type_Id + " AND MM.Store_Category_Id=" + store_category_id + " ORDER BY M.Menu_Sequence", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    MenuMaster ch = new MenuMaster();
                    ch.setMenuId(dbcursor.getInt(dbcursor.getColumnIndexOrThrow("Menu_Id")));
                    ch.setMenuName(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Menu_Name")));
                    ch.setNormalIcon(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Normal_Icon")));
                    ch.setTickIcon(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Tick_Icon")));
                    ch.setGreyIcon(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Grey_Icon")));
                    ch.setMenuPath(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Menu_Path")));
                    list.add(ch);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {
            Log.d("Exc get menu list!", e.toString());
            return list;
        }
        Log.d("Fetching check->Stop<", "-");
        return list;
    }


    //usk
    public boolean insertMappingPosmData(MappingPosmGetterSetter storeTypeMaster) {
        db.delete("Mapping_Posm", null, null);
        ContentValues values = new ContentValues();
        List<MappingPosm> data = storeTypeMaster.getMappingPosm();
        try {
            if (data.size() == 0) {
                return false;
            }

            for (int i = 0; i < data.size(); i++) {
                values.put("Posm_Id", data.get(i).getPosmId());
                values.put("Store_Type_Id", data.get(i).getStoreTypeId());
                values.put("State_Id", data.get(i).getStateId());
                values.put("Store_Category_Id", data.get(i).getStoreCategoryId());

                long id = db.insert("Mapping_Posm", null, values);
                if (id == -1) {
                    throw new Exception();
                }
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.d("Database Exception  ", ex.toString());
            return false;
        }
    }

    public boolean insertPosmMasterData(PosmMasterGetterSetter storeTypeMaster) {
        db.delete("Posm_Master", null, null);
        ContentValues values = new ContentValues();
        List<PosmMaster> data = storeTypeMaster.getPosmMaster();
        try {
            if (data.size() == 0) {
                return false;
            }

            for (int i = 0; i < data.size(); i++) {

                values.put("Posm", data.get(i).getPosm());
                values.put("Posm_Id", data.get(i).getPosmId());
                values.put("Posm_Type", data.get(i).getPosmType());
                values.put("Posm_Type_Id", data.get(i).getPosmTypeId());

                long id = db.insert("Posm_Master", null, values);
                if (id == -1) {
                    throw new Exception();
                }
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.d("Database Exception  ", ex.toString());
            return false;
        }
    }

    public boolean insertMapppingVisicoolerData(MappingVisicoolerGetterSetter storeTypeMaster) {
        db.delete("Mapping_Visicooler", null, null);
        ContentValues values = new ContentValues();
        List<MappingVisicooler> data = storeTypeMaster.getMappingVisicooler();
        try {
            if (data.size() == 0) {
                return false;
            }

            for (int i = 0; i < data.size(); i++) {

                values.put("Store_id", data.get(i).getStoreId());

                long id = db.insert("Mapping_Visicooler", null, values);
                if (id == -1) {
                    throw new Exception();
                }
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.d("Database Exception  ", ex.toString());
            return false;
        }
    }


    public boolean insertNonPosmReseonData(NonExecutionReasonGetterSetter storeTypeMaster) {
        db.delete("Non_Execution_Reason", null, null);
        ContentValues values = new ContentValues();
        List<NonExecutionReason> data = storeTypeMaster.getNonExecutionReason();
        try {
            if (data.size() == 0) {
                return false;
            }
            for (int i = 0; i < data.size(); i++) {
                values.put("Menu_Id", data.get(i).getMenuId());
                values.put("Reason", data.get(i).getReason());
                values.put("Reason_Id", data.get(i).getReasonId());

                long id = db.insert("Non_Execution_Reason", null, values);
                if (id == -1) {
                    throw new Exception();
                }
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.d("Database Exception  ", ex.toString());
            return false;
        }
    }

    public boolean insertMappingFocusSkuData(MappingFocusSkuGetterSetter storeTypeMaster) {
        db.delete("Mapping_Stock", null, null);
        ContentValues values = new ContentValues();
        List<MappingFocusSku> data = storeTypeMaster.getMappingFocusSku();
        try {
            if (data.size() == 0) {
                return false;
            }
            for (int i = 0; i < data.size(); i++) {
                values.put("Sku_Id", data.get(i).getSkuId());
                values.put("State_Id", data.get(i).getStateId());
                values.put("Store_Category_Id", data.get(i).getStoreCategoryId());
                values.put("Store_Type_Id", data.get(i).getStoreTypeId());
                values.put("Distributor_Id", data.get(i).getDistributerId());
                values.put("Focus", data.get(i).getFocusId());

                long id = db.insert("Mapping_Stock", null, values);
                if (id == -1) {
                    throw new Exception();
                }
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.d("Database Exception  ", ex.toString());
            return false;
        }
    }

    public boolean insertSkuMasterData(SkuMasterGetterSetter storeTypeMaster) {
        db.delete("Sku_Master", null, null);
        ContentValues values = new ContentValues();
        List<SkuMaster> data = storeTypeMaster.getSkuMaster();
        try {
            if (data.size() == 0) {
                return false;
            }
            for (int i = 0; i < data.size(); i++) {
                values.put("Brand_Id", data.get(i).getBrandId());
                values.put("Sku", data.get(i).getSku());
                values.put("Sku_Id", data.get(i).getSkuId());
                values.put("Sku_Sequence", data.get(i).getSkuSequence());

                long id = db.insert("Sku_Master", null, values);
                if (id == -1) {
                    throw new Exception();
                }
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.d("Database Exception  ", ex.toString());
            return false;
        }
    }



    public ArrayList<PosmMaster> getastockAnswerData() {
        ArrayList<PosmMaster> list = new ArrayList<>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("Select -1 as Answer_Id, 'Select' as Answer union Select 1 as Answer_Id, 'Yes' as Answer union Select 0 as Answer_Id, 'No' as Answer", null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    PosmMaster df = new PosmMaster();
                    df.setAnswerId(Integer.valueOf(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Answer_Id"))));
                    df.setAnswer(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Answer")));

                    list.add(df);
                    dbcursor.moveToNext();
                }

                dbcursor.close();
                return list;
            }
        } catch (Exception e) {

            return list;
        }

        return list;

    }


    public ArrayList<NonExecutionReason> getPOSMReason(Integer menu_id) {
        Log.d("Fetching Data", "------------------");
        Cursor dbcursor = null;
        ArrayList<NonExecutionReason> list = new ArrayList<>();
        NonExecutionReason reason1 = new NonExecutionReason();
        try {
            reason1.setReasonId(0);
            reason1.setReason("Select Reason");
            list.add(reason1);

            dbcursor = db.rawQuery("select * from Non_Execution_Reason where Menu_Id ='" + menu_id + "' ", null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    NonExecutionReason reason = new NonExecutionReason();
                    reason.setReason(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Reason")));
                    reason.setReasonId(Integer.valueOf(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Reason_Id"))));
                    list.add(reason);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception ", e.getMessage());
            return list;
        }
        return list;
    }

    public ArrayList<CommonChillerDataGetterSetter> getPOSMDeploymentSavedData(Integer storeId, String visit_date) {
        Log.d("Fetching Data", "------------------");
        Cursor dbcursor = null;
        ArrayList<CommonChillerDataGetterSetter> list = new ArrayList<>();
        try {
            dbcursor = db.rawQuery("SELECT  * from "
                    + CommonString.TABLE_POSM_DEPLOYMENT + " where "
                    + CommonString.KEY_STORE_ID + " = '" + storeId + "' and " + CommonString.KEY_VISIT_DATE + " =  '" + visit_date + "' ", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    CommonChillerDataGetterSetter psd = new CommonChillerDataGetterSetter();

                    psd.setStore_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
                    psd.setVisit_date(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_VISIT_DATE)));
                    psd.setImg1(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_IMAGE1)));
                    psd.setExist(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_EXIST)));
                    psd.setPosm(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_POSM)));
                    psd.setPosm_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_POSM_ID)));
                    psd.setReason(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_REASON)));
                    psd.setReason_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_REASON_ID)));

                    list.add(psd);
                    dbcursor.moveToNext();

                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception ", e.getMessage());
            return list;
        }
        return list;
    }

    public ArrayList<CommonChillerDataGetterSetter> getPOSMDeploymentData(JourneyPlan jcpGetset) {
        ArrayList<CommonChillerDataGetterSetter> list = new ArrayList<CommonChillerDataGetterSetter>();
        Cursor dbcursor = null;
        try {

            dbcursor = db.rawQuery(" select distinct PM.Posm,PM.Posm_Type_Id,MP.Posm_Id,MP.State_Id,MP.Store_Type_Id from Mapping_Posm MP " +
                    " inner join Posm_Master PM ON MP.Posm_Id = PM.Posm_Id  where MP.Store_Type_Id = '" + jcpGetset.getStoreTypeId() + "' " +
                    " and  MP.State_Id = '" + jcpGetset.getStateId() + "' and MP.Store_Category_Id = '" + jcpGetset.getStoreCategoryId() + "' ", null);

            if (dbcursor != null) {

                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    CommonChillerDataGetterSetter sb = new CommonChillerDataGetterSetter();
                    sb.setPosm_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Posm_Id")));
                    sb.setPosm((((dbcursor.getString(dbcursor.getColumnIndexOrThrow("Posm"))))));
                    sb.setState_id(((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STATE_ID)))));
                    sb.setStore_type_id((((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE_TYPE_ID))))));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {
            Log.d("Exception get JCP!", e.toString());
            return list;
        }
        return list;
    }


    public long insertPOSMDeploymentData(ArrayList<CommonChillerDataGetterSetter> deploymentData, JourneyPlan jcp) {

        db.delete(CommonString.TABLE_POSM_DEPLOYMENT, "STORE_ID ='" + jcp.getStoreId() + "'", null);
        ContentValues values = new ContentValues();

        long id = 0;
        try {
            for (int i = 0; i < deploymentData.size(); i++) {
                values.put(CommonString.KEY_STORE_ID, jcp.getStoreId());
                values.put(CommonString.KEY_VISIT_DATE, jcp.getVisitDate());
                values.put(CommonString.KEY_EXIST, deploymentData.get(i).getExist());
                if (deploymentData.get(i).getExist().equalsIgnoreCase("1")) {
                    values.put(CommonString.KEY_IMAGE1, deploymentData.get(i).getImg1());
                    values.put(CommonString.KEY_REASON_ID, "");
                    values.put(CommonString.KEY_REASON, "");
                } else {
                    values.put(CommonString.KEY_IMAGE1, "");
                    values.put(CommonString.KEY_REASON_ID, deploymentData.get(i).getReason_id());
                    values.put(CommonString.KEY_REASON, deploymentData.get(i).getReason());
                }
                values.put(CommonString.KEY_POSM_ID, deploymentData.get(i).getPosm_id());
                values.put(CommonString.KEY_POSM, deploymentData.get(i).getPosm());

                id = db.insert(CommonString.TABLE_POSM_DEPLOYMENT, null, values);

            }

            if (id > 0) {
                return id;
            } else {
                return 0;
            }
        } catch (Exception ex) {
            Log.d("Database Exception ", ex.toString());
            return 0;
        }
    }


    public long insertVisiCoolerData(JourneyPlan JCP, VisiColoersGetterSetter storeProfile) {
        db.delete(CommonString.TABLE_VISICOOLER, " STORE_ID" + "='" + JCP.getStateId() + "' AND VISIT_DATE='" + JCP.getVisitDate() + "'", null);
        ContentValues values = new ContentValues();
        long l = 0;
        try {
            db.beginTransaction();
            if (!JCP.getStoreName().isEmpty()) {
                values.put(CommonString.KEY_STORE_ID, JCP.getStoreId());
                values.put(CommonString.KEY_VISIT_DATE, JCP.getVisitDate());
                values.put(CommonString.KEY_PRESENT_EXIST, storeProfile.getPresent_name());
                values.put(CommonString.KEY_WORKING_EXIST, storeProfile.getWorking_name());
                values.put(CommonString.KEY_LOCATION_EXIST, storeProfile.getLocation());
                values.put(CommonString.KEY_PURITY_EXIST, storeProfile.getPurity_name());
                values.put(CommonString.KEY_PLANOGRAM_EXIST, storeProfile.getPlanogram_name());
                values.put(CommonString.KEY_IMAGE_CLOSEUP, storeProfile.getImage_close_up());
                values.put(CommonString.KEY_IMAGE_LONGSHOT, storeProfile.getImage_long_shot());

                l = db.insert(CommonString.TABLE_VISICOOLER, null, values);
            }
            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (Exception ex) {
            Log.d("Database Exception", " while Insert Header Data " + ex.toString());
        }
        return l;
    }

    public VisiColoersGetterSetter getVisicoolerData(JourneyPlan jcp) {

        VisiColoersGetterSetter sb = new VisiColoersGetterSetter();
        Cursor dbcursor = null;

        try {

            dbcursor = db.rawQuery("SELECT * FROM " + CommonString.TABLE_VISICOOLER + " WHERE " + CommonString.KEY_STORE_ID + " = " + jcp.getStoreId(), null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    sb.setPresent_name(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_PRESENT_EXIST)));
                    sb.setWorking_name(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_WORKING_EXIST)));
                    sb.setLocation(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_LOCATION_EXIST)));
                    sb.setPurity_name(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_PURITY_EXIST)));
                    sb.setPlanogram_name(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_PLANOGRAM_EXIST)));
                    sb.setImage_close_up(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_IMAGE_CLOSEUP)));
                    sb.setImage_long_shot(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_IMAGE_LONGSHOT)));


                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return sb;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching", e.toString());
            return sb;
        }

        Log.d("Fetching non working", "-------------------");
        return sb;
    }


    public ArrayList<FocusProductGetterSetter> getHeaderSalesData(JourneyPlan jcp) {
        Log.d("Fetching", "Storedata--------------->Start<------------");
        ArrayList<FocusProductGetterSetter> list = new ArrayList<FocusProductGetterSetter>();
        Cursor dbcursor = null;

        try {

            dbcursor = db.rawQuery("Select distinct br.Brand_Id, br.Brand from Mapping_Focus_Sku m " +
                    " inner join Sku_Master sk on m.Sku_Id = sk.Sku_Id " +
                    " inner join Brand_Master br on sk.Brand_Id = br.Brand_Id " +
                    " where m.State_Id = '" + jcp.getStateId() + "' and m.Store_Category_Id = '" + jcp.getStoreCategoryId() + "' and m.Store_Type_Id = '" + jcp.getStoreTypeId() + "'", null);


            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    FocusProductGetterSetter sb = new FocusProductGetterSetter();
                    sb.setBrand_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Brand_Id")));
                    sb.setBrand(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Brand")));

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {
            Log.d("Exception", " when fetching Header!!!!!!!!!!! " + e.toString());
            return list;
        }
        Log.d("Fetching ", "Header stock---------------------->Stop<-----------");
        return list;
    }

    public ArrayList<FocusProductGetterSetter> getSalesStockInsertedData(JourneyPlan jcp, String brand_id) {
        Log.d("Fetching", "Storedata--------------->Start<------------");
        ArrayList<FocusProductGetterSetter> list = new ArrayList<>();
        Cursor dbcursor = null;

        try {

            dbcursor = db.rawQuery("SELECT * FROM FOCUS_PRODUCT_STOCK_DATA WHERE STORE_ID ='" + jcp.getStoreId() + "' AND BRAND_CD=" + brand_id + "", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    FocusProductGetterSetter sb = new FocusProductGetterSetter();
                    sb.setSku(dbcursor.getString(dbcursor.getColumnIndexOrThrow("SKU")));
                    sb.setSku_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow("SKU_CD")));
                    sb.setStock(dbcursor.getString(dbcursor.getColumnIndexOrThrow("STOCK")));

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {
            Log.d("Exception ", "when fetching opening stock!!!!!!!!!!!" + e.toString());
            return list;
        }

        Log.d("Fetching ", "opening stock---------------------->Stop<-----------");
        return list;
    }

    public ArrayList<FocusProductGetterSetter> getSalesStockchildData(JourneyPlan jcp, String brand_id) {
        Log.d("Fetching", "Storedata--------------->Start<------------");
        ArrayList<FocusProductGetterSetter> list = new ArrayList<>();
        Cursor dbcursor = null;

        try {
            dbcursor = db.rawQuery("Select distinct sk.Sku_Id, sk.Sku from Mapping_Focus_Sku m " +
                    " inner join Sku_Master sk on m.Sku_Id = sk.Sku_Id " +
                    " inner join Brand_Master br on sk.Brand_Id = br.Brand_Id " +
                    " where m.State_Id = '" + jcp.getStateId() + "' and m.Store_Category_Id = '" + jcp.getStoreCategoryId() + "' and m.Store_Type_Id = '" + jcp.getStoreTypeId() + "' and br.Brand_Id = '" + brand_id + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    FocusProductGetterSetter sb = new FocusProductGetterSetter();
                    sb.setSku_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Sku_Id")));
                    sb.setSku(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Sku")));

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {
            Log.d("Exception ", "when fetching opening stock!!!!!!!!!!!" + e.toString());
            return list;
        }

        Log.d("Fetching ", "opening stock---------------------->Stop<-----------");
        return list;
    }


    public void insertSalesStockData(JourneyPlan jcp,
                                     HashMap<FocusProductGetterSetter,
                                             List<FocusProductGetterSetter>> data, List<FocusProductGetterSetter> save_listDataHeader) {
        db.delete(CommonString.TABLE_INSERT_FOCUS_PRODUCT_STOCK_OPENINGHEADER_DATA, "STORE_ID ='" + jcp.getStoreId() + "'", null);
        db.delete(CommonString.TABLE_STORE_FOCUS_PRODUCT_DATA, "STORE_ID ='" + jcp.getStoreId() + "'", null);

        ContentValues values = new ContentValues();
        ContentValues values1 = new ContentValues();

        try {
            db.beginTransaction();
            for (int i = 0; i < save_listDataHeader.size(); i++) {
                values.put("STORE_ID", jcp.getStoreId());
                values.put(CommonString.KEY_VISIT_DATE, jcp.getVisitDate());
                values.put("BRAND_CD", save_listDataHeader.get(i).getBrand_id());
                values.put("BRAND", save_listDataHeader.get(i).getBrand());
                long l = db.insert(CommonString.TABLE_INSERT_FOCUS_PRODUCT_STOCK_OPENINGHEADER_DATA, null, values);
                for (int j = 0; j < data.get(save_listDataHeader.get(i)).size(); j++) {
                    values1.put("Common_Id", (int) l);
                    values1.put("STORE_ID", jcp.getStoreId());
                    values1.put(CommonString.KEY_VISIT_DATE, jcp.getVisitDate());
                    values1.put("BRAND_CD", save_listDataHeader.get(i).getBrand_id());
                    values1.put("BRAND", save_listDataHeader.get(i).getBrand());
                    values1.put("SKU", data.get(save_listDataHeader.get(i)).get(j).getSku());
                    values1.put("SKU_CD", data.get(save_listDataHeader.get(i)).get(j).getSku_id());
                    if (!data.get(save_listDataHeader.get(i)).get(j).getStock().equals("")) {
                        values1.put("STOCK", Integer.parseInt(data.get(save_listDataHeader.get(i)).get(j).getStock()));
                    } else {
                        values1.put("STOCK", "0");
                    }
                    db.insert(CommonString.TABLE_STORE_FOCUS_PRODUCT_DATA, null, values1);
                }
            }
            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (Exception ex) {
            Log.d("Database Exception", " while Insert Posm Master Data " + ex.toString());
        }
    }

    public ArrayList<ChecklistAnswer> getVisicoolereason(Integer cheklist_id) {
        Log.d("Fetching Data", "------------------");
        Cursor dbcursor = null;
        ArrayList<ChecklistAnswer> list = new ArrayList<>();
        ChecklistAnswer reason1 = new ChecklistAnswer();
        try {

            reason1.setAnswerId(0);
            reason1.setAnswer("Select ");
            list.add(0, reason1);

            dbcursor = db.rawQuery("select * from Checklist_Answer where checklist_Id ='" + cheklist_id + "' ", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    ChecklistAnswer reason = new ChecklistAnswer();
                    reason.setAnswer(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Answer")));
                    reason.setAnswerId(Integer.valueOf(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Answer_Id"))));
                    reason.setChecklistId(Integer.valueOf(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Checklist_Id"))));

                    list.add(reason);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception ", e.getMessage());
            return list;
        }
        return list;
    }


    public ArrayList<VisiColoersGetterSetter> getVisicoolerSavedData(Integer storeId, String visit_date) {
        Log.d("Fetching Data", "------------------");
        Cursor dbcursor = null;
        ArrayList<VisiColoersGetterSetter> list = new ArrayList<>();
        try {
            dbcursor = db.rawQuery("SELECT  * from "
                    + CommonString.TABLE_VISICOOLER_CHEKLIST + " where "
                    + CommonString.KEY_STORE_ID + " = '" + storeId + "' and " + CommonString.KEY_VISIT_DATE + " =  '" + visit_date + "' ", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    VisiColoersGetterSetter psd = new VisiColoersGetterSetter();
                    psd.setStore_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
                    psd.setVisit_date(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_VISIT_DATE)));
                    psd.setCheklist_cd(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_CHEKLIST_ID)));
                    psd.setCheklist(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_CHEKLIST)));
                    psd.setAnswer_cd(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_ANSWER_CD)));
                    psd.setAnswer(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_ANSWER)));

                    list.add(psd);
                    dbcursor.moveToNext();

                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception ", e.getMessage());
            return list;
        }
        return list;
    }

    public ArrayList<VisiColoersGetterSetter> getVISICOOLER_Data(JourneyPlan jcpGetset, Integer menu_id) {
        ArrayList<VisiColoersGetterSetter> list = new ArrayList<VisiColoersGetterSetter>();
        Cursor dbcursor = null;
        try {

            dbcursor = db.rawQuery("select m.Checklist_Id, c.Checklist from Mapping_Menu_Checklist m inner join Checklist_Master c on m.Checklist_Id = c.Checklist_Id" +
                    " Where Menu_Id = '" + menu_id + "' ", null);


            if (dbcursor != null) {

                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    VisiColoersGetterSetter sb = new VisiColoersGetterSetter();
                    sb.setCheklist(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Checklist")));
                    sb.setCheklist_cd((((dbcursor.getString(dbcursor.getColumnIndexOrThrow("Checklist_Id"))))));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {
            Log.d("Exception get JCP!", e.toString());
            return list;
        }
        return list;
    }

    public long insertNewVisiCoolerData(JourneyPlan JCP, VisiColoersGetterSetter visicoolar, ArrayList<VisiColoersGetterSetter> deploymentData) {

        db.delete(CommonString.TABLE_VISICOOLER_DATA, "STORE_ID ='" + JCP.getStoreId() + "'", null);
        db.delete(CommonString.TABLE_VISICOOLER_CHEKLIST, "STORE_ID ='" + JCP.getStoreId() + "'", null);
        ContentValues values = new ContentValues();
        ContentValues values1 = new ContentValues();
        long l = 0;
        try {
            db.beginTransaction();
            if (!JCP.getStoreName().isEmpty()) {
                values.put(CommonString.KEY_STORE_ID, JCP.getStoreId());
                values.put(CommonString.KEY_VISIT_DATE, JCP.getVisitDate());
                values.put(CommonString.KEY_PRESENT_EXIST, visicoolar.getPresent_name());
                values.put(CommonString.KEY_IMAGE_CLOSEUP, visicoolar.getImage_close_up());
                values.put(CommonString.KEY_IMAGE_LONGSHOT, visicoolar.getImage_long_shot());
                values.put(CommonString.KEY_REASON, visicoolar.getReason());
                values.put(CommonString.KEY_REASON_ID, visicoolar.getReason_cd());
                l = db.insert(CommonString.TABLE_VISICOOLER_DATA, null, values);

                if (1 > 0) {
                    long id = 0;
                    for (int i = 0; i < deploymentData.size(); i++) {
                        values1.put(CommonString.KEY_STORE_ID, JCP.getStoreId());
                        values1.put(CommonString.KEY_VISIT_DATE, JCP.getVisitDate());
                        values1.put(CommonString.KEY_ANSWER_CD, deploymentData.get(i).getAnswer_cd());
                        values1.put(CommonString.KEY_ANSWER, deploymentData.get(i).getAnswer());
                        values1.put(CommonString.KEY_CHEKLIST, deploymentData.get(i).getCheklist());
                        values1.put(CommonString.KEY_CHEKLIST_ID, deploymentData.get(i).getCheklist_cd());
                        id = db.insert(CommonString.TABLE_VISICOOLER_CHEKLIST, null, values1);

                    }
                }

            }
            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (Exception ex) {
            Log.d("Database Exception", " while Insert Header Data " + ex.toString());
        }
        return l;
    }

    public VisiColoersGetterSetter getNewVisicoolerData(JourneyPlan jcp) {

        VisiColoersGetterSetter sb = new VisiColoersGetterSetter();
        Cursor dbcursor = null;

        try {

            dbcursor = db.rawQuery("SELECT * FROM " + CommonString.TABLE_VISICOOLER_DATA + " WHERE " + CommonString.KEY_STORE_ID + " = " + jcp.getStoreId(), null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    sb.setPresent_name(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_PRESENT_EXIST)));
                    sb.setImage_close_up(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_IMAGE_CLOSEUP)));
                    sb.setImage_long_shot(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_IMAGE_LONGSHOT)));
                    sb.setReason(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_REASON)));
                    sb.setReason_cd(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_REASON_ID)));

                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return sb;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching", e.toString());
            return sb;
        }

        Log.d("Fetching non working", "-------------------");
        return sb;
    }

    public long insertMonkysunData(JourneyPlan JCP, VisiColoersGetterSetter visicoolar, ArrayList<VisiColoersGetterSetter> deploymentData) {
        db.delete(CommonString.TABLE_MONKEUSUN_DATA, "STORE_ID ='" + JCP.getStoreId() + "'", null);
        db.delete(CommonString.TABLE_MONKEUSUN_CHEKLIST, "STORE_ID ='" + JCP.getStoreId() + "'", null);
        ContentValues values = new ContentValues();
        ContentValues values1 = new ContentValues();
        long l = 0;
        try {
            db.beginTransaction();
            if (!JCP.getStoreName().isEmpty()) {
                values.put(CommonString.KEY_STORE_ID, JCP.getStoreId());
                values.put(CommonString.KEY_VISIT_DATE, JCP.getVisitDate());
                values.put(CommonString.KEY_PRESENT_EXIST, visicoolar.getPresent_name());
                values.put(CommonString.KEY_IMAGE_CLOSEUP, visicoolar.getImage_close_up());
                values.put(CommonString.KEY_LONGSHOT, visicoolar.getImage_long_shot());
                values.put(CommonString.KEY_REASON, visicoolar.getReason());
                values.put(CommonString.KEY_REASON_ID, visicoolar.getReason_cd());
                l = db.insert(CommonString.TABLE_MONKEUSUN_DATA, null, values);

                if (1 > 0) {
                    long id = 0;
                    for (int i = 0; i < deploymentData.size(); i++) {
                        values1.put(CommonString.KEY_STORE_ID, JCP.getStoreId());
                        values1.put(CommonString.KEY_VISIT_DATE, JCP.getVisitDate());
                        values1.put(CommonString.KEY_ANSWER_CD, deploymentData.get(i).getAnswer_cd());
                        values1.put(CommonString.KEY_ANSWER, deploymentData.get(i).getAnswer());
                        values1.put(CommonString.KEY_CHEKLIST, deploymentData.get(i).getCheklist());
                        values1.put(CommonString.KEY_CHEKLIST_ID, deploymentData.get(i).getCheklist_cd());
                        id = db.insert(CommonString.TABLE_MONKEUSUN_CHEKLIST, null, values1);

                    }
                }

            }
            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (Exception ex) {
            Log.d("Database Exception", " while Insert Header Data " + ex.toString());
        }
        return l;
    }


    public VisiColoersGetterSetter getMonkyHeaderData(JourneyPlan jcp) {

        VisiColoersGetterSetter sb = new VisiColoersGetterSetter();
        Cursor dbcursor = null;

        try {

            dbcursor = db.rawQuery("SELECT * FROM " + CommonString.TABLE_MONKEUSUN_DATA + " WHERE " + CommonString.KEY_STORE_ID + " = " + jcp.getStoreId(), null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    sb.setPresent_name(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_PRESENT_EXIST)));
                    sb.setImage_close_up(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_IMAGE_CLOSEUP)));
                    sb.setImage_long_shot(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_LONGSHOT)));
                    sb.setReason(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_REASON)));
                    sb.setReason_cd(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_REASON_ID)));

                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return sb;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching", e.toString());
            return sb;
        }

        Log.d("Fetching non working", "-------------------");
        return sb;
    }

    public ArrayList<VisiColoersGetterSetter> getMonkeysunSavedData(Integer storeId, String visit_date) {
        Log.d("Fetching Data", "------------------");
        Cursor dbcursor = null;
        ArrayList<VisiColoersGetterSetter> list = new ArrayList<>();
        try {
            dbcursor = db.rawQuery("SELECT  * from "
                    + CommonString.TABLE_MONKEUSUN_CHEKLIST + " where "
                    + CommonString.KEY_STORE_ID + " = '" + storeId + "' and " + CommonString.KEY_VISIT_DATE + " =  '" + visit_date + "' ", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    VisiColoersGetterSetter psd = new VisiColoersGetterSetter();
                    psd.setStore_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
                    psd.setVisit_date(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_VISIT_DATE)));
                    psd.setCheklist_cd(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_CHEKLIST_ID)));
                    psd.setCheklist(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_CHEKLIST)));
                    psd.setAnswer_cd(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_ANSWER_CD)));
                    psd.setAnswer(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_ANSWER)));

                    list.add(psd);
                    dbcursor.moveToNext();

                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception ", e.getMessage());
            return list;
        }
        return list;
    }

    public BackofStoreGetterSetter getBackofStoreData(JourneyPlan jcp) {

        BackofStoreGetterSetter sb = new BackofStoreGetterSetter();
        Cursor dbcursor = null;

        try {

            dbcursor = db.rawQuery("SELECT * FROM " + CommonString.TABLE_BACKOF_STORE_HEADER_DATA + " WHERE " + CommonString.KEY_STORE_ID + " = " + jcp.getStoreId(), null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    sb.setPresent_name(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_PRESENT_EXIST)));
                    sb.setImage_close_up(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_IMAGE_CLOSEUP)));
                    sb.setImage_long_shot(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_LONGSHOT)));
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return sb;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching", e.toString());
            return sb;
        }

        Log.d("Fetching non working", "-------------------");
        return sb;
    }

    public long insertBackofStoreData(JourneyPlan JCP, BackofStoreGetterSetter backofstore) {

        db.delete(CommonString.TABLE_BACKOF_STORE_HEADER_DATA, "STORE_ID ='" + JCP.getStoreId() + "'", null);
        ContentValues values = new ContentValues();
        long l = 0;
        try {
            db.beginTransaction();
            if (!JCP.getStoreName().isEmpty()) {
                values.put(CommonString.KEY_STORE_ID, JCP.getStoreId());
                values.put(CommonString.KEY_VISIT_DATE, JCP.getVisitDate());
                values.put(CommonString.KEY_PRESENT_EXIST, backofstore.getPresent_name());
                values.put(CommonString.KEY_IMAGE_CLOSEUP, backofstore.getImage_close_up());
                values.put(CommonString.KEY_LONGSHOT, backofstore.getImage_long_shot());
                l = db.insert(CommonString.TABLE_BACKOF_STORE_HEADER_DATA, null, values);

            }
            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (Exception ex) {
            Log.d("Database Exception", " while Insert Header Data " + ex.toString());
        }
        return l;
    }


    public ArrayList<BackofStoreGetterSetter> getHeaderBackofStoreData(JourneyPlan jcp) {
        ArrayList<BackofStoreGetterSetter> list = new ArrayList<BackofStoreGetterSetter>();
        Cursor dbcursor = null;

        try {

            dbcursor = db.rawQuery("Select distinct br.Brand_Id, br.Brand from Brand_Master br " +
                    " inner join mapping_Back_Of_Store mb on br.Brand_Id = mb.Brand_Id " +
                    " where mb.State_Id = '" + jcp.getStateId() + "' and mb.Store_Category_Id = '" + jcp.getStoreCategoryId() + "' and mb.Store_Type_Id = '" + jcp.getStoreTypeId() + "'", null);


            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    BackofStoreGetterSetter sb = new BackofStoreGetterSetter();
                    sb.setBrand_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Brand_Id")));
                    sb.setBrand(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Brand")));

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {
            Log.d("Exception", " when fetching Header!!!!!!!!!!! " + e.toString());
            return list;
        }
        Log.d("Fetching ", "Header stock---------------------->Stop<-----------");
        return list;
    }



    public ArrayList<BackofStoreGetterSetter> getBackofStoreChildData(MenuMaster menu) {
        Log.d("Fetching", "Storedata--------------->Start<------------");
        ArrayList<BackofStoreGetterSetter> list = new ArrayList<>();
        Cursor dbcursor = null;

        try {

            dbcursor = db.rawQuery("Select distinct c.Checklist_Id, c.Checklist from Mapping_Menu_Checklist m " +
                    " inner join Checklist_Master c on m.Checklist_Id = c.Checklist_Id " +
                    " where m.Menu_Id = '" + menu.getMenuId() + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    BackofStoreGetterSetter sb = new BackofStoreGetterSetter();
                    sb.setChecklist_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Checklist_Id")));
                    sb.setChecklist(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Checklist")));
                    sb.setAnswerId(0);
                    sb.setAnswer("");
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {
            Log.d("Exception ", "when fetching opening stock!!!!!!!!!!!" + e.toString());
            return list;
        }

        Log.d("Fetching ", "opening stock---------------------->Stop<-----------");
        return list;
    }

    public ArrayList<BackofStoreGetterSetter> getReasonBackofStore_Data(String cheklist_id) {
        ArrayList<BackofStoreGetterSetter> list = new ArrayList<BackofStoreGetterSetter>();
        Cursor dbcursor = null;
        try {

            dbcursor = db.rawQuery("select * from Checklist_Answer where Checklist_Id ='" + cheklist_id + "' ", null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    BackofStoreGetterSetter reason = new BackofStoreGetterSetter();
                    reason.setReason(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Answer")));
                    reason.setReasonId(Integer.valueOf(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Answer_Id"))));
                    list.add(reason);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {
            Log.d("Exception get JCP!", e.toString());
            return list;
        }
        return list;
    }


    public void InsertBackofStoreData(JourneyPlan jcp,
                                      HashMap<BackofStoreGetterSetter, List<BackofStoreGetterSetter>> data, List<BackofStoreGetterSetter> save_listDataHeader) {

        db.delete(CommonString.TABLE_HEADER_BACK_OF_STORE, CommonString.KEY_STORE_ID + "='" + jcp.getStoreId() + "'", null);
        db.delete(CommonString.TABLE_CHILD_BACK_OF_STORE_DATA, CommonString.KEY_STORE_ID + "='" + jcp.getStoreId() + "'", null);
        ContentValues values = new ContentValues();
        ContentValues values1 = new ContentValues();


        try {
            db.beginTransaction();
            for (int i = 0; i < save_listDataHeader.size(); i++) {
                values.put(CommonString.KEY_STORE_ID, jcp.getStoreId());
                values.put(CommonString.KEY_VISIT_DATE, jcp.getVisitDate());
                values.put(CommonString.KEY_BRAND_CD, save_listDataHeader.get(i).getBrand_id());
                values.put(CommonString.KEY_BRAND, save_listDataHeader.get(i).getBrand());
                values.put(CommonString.KEY_STOCK, save_listDataHeader.get(i).getStock());

                long l = db.insert(CommonString.TABLE_HEADER_BACK_OF_STORE, null, values);

                for (int j = 0; j < data.get(save_listDataHeader.get(i)).size(); j++) {
                    values1.put(CommonString.KEY_COMMON_ID, (int) l);
                    values1.put(CommonString.KEY_STORE_ID, jcp.getStoreId());
                    values1.put(CommonString.KEY_VISIT_DATE, jcp.getVisitDate());
                    values1.put(CommonString.KEY_BRAND_CD, save_listDataHeader.get(i).getBrand_id());
                    values1.put(CommonString.KEY_BRAND, save_listDataHeader.get(i).getBrand());
                    values1.put(CommonString.KEY_REASON_ID, data.get(save_listDataHeader.get(i)).get(j).getAnswerId());
                    values1.put(CommonString.KEY_REASON, data.get(save_listDataHeader.get(i)).get(j).getAnswer());
                    values1.put(CommonString.KEY_CHECKLIST_ID, data.get(save_listDataHeader.get(i)).get(j).getChecklist_id());
                    values1.put(CommonString.KEY_CHEKLIST, data.get(save_listDataHeader.get(i)).get(j).getChecklist());

                    db.insert(CommonString.TABLE_CHILD_BACK_OF_STORE_DATA, null, values1);
                }
            }
            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (Exception ex) {
            Log.d("Database Exception", " while Insert Posm Master Data " + ex.toString());
        }
    }

    public ArrayList<BackofStoreGetterSetter> getChildCheklistBackofStoreInsertData(JourneyPlan jcp, Integer coomin_id) {
        Log.d("Fetching", "Storedata--------------->Start<------------");
        ArrayList<BackofStoreGetterSetter> list = new ArrayList<>();
        Cursor dbcursor = null;

        try {
            dbcursor = db.rawQuery("SELECT * FROM DR_CHILD_BACK_OF_STORE_DATA WHERE STORE_ID ='" + jcp.getStoreId() + "' AND COMMON_ID='" + coomin_id + "' ", null);


            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    BackofStoreGetterSetter sb = new BackofStoreGetterSetter();
                    sb.setAnswer(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_REASON)));
                    sb.setAnswerId((dbcursor.getInt(dbcursor.getColumnIndexOrThrow(CommonString.KEY_REASON_ID))));
                    sb.setChecklist_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_CHECKLIST_ID)));
                    sb.setChecklist(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_CHEKLIST)));

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {
            Log.d("Exception ", "when fetching opening stock!!!!!!!!!!!" + e.toString());
            return list;
        }

        Log.d("Fetching ", "opening stock---------------------->Stop<-----------");
        return list;
    }

    public ArrayList<BackofStoreGetterSetter> getInsertBackofStoreHeaderData(JourneyPlan jcp) {
        Log.d("Fetching", "Storedata--------------->Start<------------");
        ArrayList<BackofStoreGetterSetter> list = new ArrayList<BackofStoreGetterSetter>();
        Cursor dbcursor = null;

        try {

            dbcursor = db.rawQuery("SELECT * FROM DR_HEADER_BACK_OF_STORE WHERE STORE_ID ='" + jcp.getStoreId() + "' ", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    BackofStoreGetterSetter sb = new BackofStoreGetterSetter();
                    sb.setBrand_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_BRAND_CD)));
                    sb.setBrand(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_BRAND)));
                    sb.setStock(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STOCK)));
                    sb.setCommon_id(dbcursor.getInt(dbcursor.getColumnIndexOrThrow(CommonString.KEY_ID)));

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {
            Log.d("Exception ", "when fetching opening stock!!!!!!!!!!!" + e.toString());
            return list;
        }

        Log.d("Fetching ", "opening stock---------------------->Stop<-----------");
        return list;
    }


    public long insertJarData(JourneyPlan JCP, JarGetterSetter visicoolar, ArrayList<JarGetterSetter> deploymentData) {

        db.delete(CommonString.TABLE_JAR_DATA, "STORE_ID ='" + JCP.getStoreId() + "'", null);
        db.delete(CommonString.TABLE_JAR_CHEKLIST, "STORE_ID ='" + JCP.getStoreId() + "'", null);
        ContentValues values = new ContentValues();
        ContentValues values1 = new ContentValues();
        long l = 0;
        try {
            db.beginTransaction();
            if (!JCP.getStoreName().isEmpty()) {
                values.put(CommonString.KEY_STORE_ID, JCP.getStoreId());
                values.put(CommonString.KEY_VISIT_DATE, JCP.getVisitDate());
                values.put(CommonString.KEY_PRESENT_EXIST, visicoolar.getPresent_name());
                values.put(CommonString.KEY_IMAGE_CLOSEUP, visicoolar.getImage_close_up());
                values.put(CommonString.KEY_LONGSHOT, visicoolar.getImage_long_shot());
                l = db.insert(CommonString.TABLE_JAR_DATA, null, values);

                if (1 > 0) {
                    long id = 0;
                    for (int i = 0; i < deploymentData.size(); i++) {
                        values1.put(CommonString.KEY_STORE_ID, JCP.getStoreId());
                        values1.put(CommonString.KEY_VISIT_DATE, JCP.getVisitDate());
                        values1.put(CommonString.KEY_ANSWER_CD, deploymentData.get(i).getAnswer_cd());
                        values1.put(CommonString.KEY_ANSWER, deploymentData.get(i).getAnswer());
                        values1.put(CommonString.KEY_CHEKLIST, deploymentData.get(i).getCheklist());
                        values1.put(CommonString.KEY_CHEKLIST_ID, deploymentData.get(i).getCheklist_cd());
                        id = db.insert(CommonString.TABLE_JAR_CHEKLIST, null, values1);

                    }
                }

            }
            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (Exception ex) {
            Log.d("Database Exception", " while Insert Header Data " + ex.toString());
        }
        return l;
    }

    public ArrayList<JarGetterSetter> getJarSavedData(Integer storeId, String visit_date) {
        Log.d("Fetching Data", "------------------");
        Cursor dbcursor = null;
        ArrayList<JarGetterSetter> list = new ArrayList<>();
        try {
            dbcursor = db.rawQuery("SELECT  * from "
                    + CommonString.TABLE_JAR_CHEKLIST + " where "
                    + CommonString.KEY_STORE_ID + " = '" + storeId + "' and " + CommonString.KEY_VISIT_DATE + " =  '" + visit_date + "' ", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    JarGetterSetter psd = new JarGetterSetter();
                    psd.setStore_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
                    psd.setVisit_date(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_VISIT_DATE)));
                    psd.setCheklist_cd(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_CHEKLIST_ID)));
                    psd.setCheklist(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_CHEKLIST)));
                    psd.setAnswer_cd(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_ANSWER_CD)));
                    psd.setAnswer(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_ANSWER)));

                    list.add(psd);
                    dbcursor.moveToNext();

                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception ", e.getMessage());
            return list;
        }
        return list;
    }

    public ArrayList<JarGetterSetter> getJar_Data(JourneyPlan jcpGetset, Integer menu_id) {
        ArrayList<JarGetterSetter> list = new ArrayList<JarGetterSetter>();
        Cursor dbcursor = null;
        try {

            dbcursor = db.rawQuery("select m.Checklist_Id, c.Checklist from Mapping_Menu_Checklist m inner join Checklist_Master c on m.Checklist_Id = c.Checklist_Id" +
                    " Where Menu_Id = '" + menu_id + "' ", null);


            if (dbcursor != null) {

                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    JarGetterSetter sb = new JarGetterSetter();
                    sb.setCheklist(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Checklist")));
                    sb.setCheklist_cd((((dbcursor.getString(dbcursor.getColumnIndexOrThrow("Checklist_Id"))))));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {
            Log.d("Exception get JCP!", e.toString());
            return list;
        }
        return list;
    }

    public JarGetterSetter getJarInsertData(JourneyPlan jcp) {
        JarGetterSetter sb = new JarGetterSetter();
        Cursor dbcursor = null;

        try {
            dbcursor = db.rawQuery("SELECT * FROM " + CommonString.TABLE_JAR_DATA + " WHERE " + CommonString.KEY_STORE_ID + " = " + jcp.getStoreId(), null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    sb.setPresent_name(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_PRESENT_EXIST)));
                    sb.setImage_close_up(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_IMAGE_CLOSEUP)));
                    sb.setImage_long_shot(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_LONGSHOT)));

                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return sb;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching", e.toString());
            return sb;
        }

        Log.d("Fetching non working", "-------------------");
        return sb;
    }



    //get checklist data
    public ArrayList<ChecklistMaster> getCheckListData(int menu_id) {
        ArrayList<ChecklistMaster> list = new ArrayList<>();
        Cursor dbcursor = null;
        try {

            dbcursor = db.rawQuery("select m.Checklist_Id, c.Checklist from Mapping_Menu_Checklist m inner join Checklist_Master c on m.Checklist_Id = c.Checklist_Id" +
                    " Where Menu_Id = '" + menu_id + "' ", null);


            if (dbcursor != null) {

                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    ChecklistMaster sb = new ChecklistMaster();
                    sb.setChecklist(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Checklist")));
                    sb.setChecklistId(dbcursor.getInt(dbcursor.getColumnIndexOrThrow("Checklist_Id")));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {
            Log.d("Excep get checklist!", e.toString());
            return list;
        }
        return list;
    }

    //get Checklist Answer
    public ArrayList<ChecklistAnswer> getCheckListAnswer(int checkListId) {
        Log.d("Fetching Data", "------------------");
        Cursor dbcursor = null;
        ArrayList<ChecklistAnswer> list = new ArrayList<>();
        ChecklistAnswer reason1 = new ChecklistAnswer();
        try {

            reason1.setAnswerId(0);
            reason1.setAnswer("Select");
            list.add(reason1);

            dbcursor = db.rawQuery("select * from Checklist_Answer where checklist_Id ='" + checkListId + "' ", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    ChecklistAnswer reason = new ChecklistAnswer();
                    reason.setAnswer(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Answer")));
                    reason.setAnswerId(Integer.valueOf(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Answer_Id"))));
                    reason.setChecklistId(Integer.valueOf(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Checklist_Id"))));

                    list.add(reason);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception ", e.getMessage());
            return list;
        }
        return list;
    }

    //get Window List data
    public ArrayList<BrandMaster> getBrandDefaultData(int Store_Type_Id, int Store_Category_Id, String State_Id, int Window_Id) {
        Log.d("Fetchecklidata->Start<-", "-");
        ArrayList<BrandMaster> list = new ArrayList<>();
        Cursor dbcursor = null;
        try {

            dbcursor = db.rawQuery("SELECT BM.Brand_Id, BM.Brand FROM Brand_Master BM INNER JOIN Mapping_Window MW ON BM.Brand_Id = MW.Brand_Id " +
                    "WHERE MW.Store_Type_Id=" + Store_Type_Id + " AND MW.Store_Category_Id=" + Store_Category_Id + " AND MW.State_Id='" + State_Id + "' AND MW.Window_Id='" + Window_Id + "'", null);


            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    BrandMaster ch = new BrandMaster();
                    ch.setBrandId(dbcursor.getInt(dbcursor.getColumnIndexOrThrow("Brand_Id")));
                    ch.setBrand(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Brand")));
                    list.add(ch);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {
            Log.d("Exc get Brand list!", e.toString());
            return list;
        }
        Log.d("Fetching check->Stop<", "-");
        return list;
    }

    //Get Non Execution reason by Menu_Id
    public ArrayList<NonExecutionReason> getNonExecutionReason(Integer menu_id) {
        Log.d("Fetching Data", "------------------");
        Cursor dbcursor = null;
        ArrayList<NonExecutionReason> list = new ArrayList<>();
        NonExecutionReason reason1 = new NonExecutionReason();
        try {
            reason1.setReasonId(0);
            reason1.setReason("Select Reason");
            list.add(reason1);

            dbcursor = db.rawQuery("select * from Non_Execution_Reason where Menu_Id ='" + menu_id + "' ", null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    NonExecutionReason reason = new NonExecutionReason();
                    reason.setReason(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Reason")));
                    reason.setReasonId(Integer.valueOf(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Reason_Id"))));
                    list.add(reason);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception ", e.getMessage());
            return list;
        }
        return list;
    }

    //insert Window Data
    public long insertWindowData(JourneyPlan jcp, ArrayList<WindowMaster> windowList, HashMap<WindowMaster, ArrayList<ChecklistMaster>> hashMapListChildData) {

        db.delete(CommonString.TABLE_WINDOW_HEADER, CommonString.KEY_STORE_ID + "=" + jcp.getStoreId() + " AND " + CommonString.KEY_VISIT_DATE
                + "='" + jcp.getVisitDate() + "'", null);
        //Window Check List
        db.delete(CommonString.TABLE_WINDOW_CHECK_LIST, CommonString.KEY_STORE_ID + "=" + jcp.getStoreId() + " AND " + CommonString.KEY_VISIT_DATE
                + "='" + jcp.getVisitDate() + "'", null);

        //Brand List
        db.delete(CommonString.TABLE_WINDOW_BRAND_LIST, CommonString.KEY_STORE_ID + "=" + jcp.getStoreId() + " AND " + CommonString.KEY_VISIT_DATE
                + "='" + jcp.getVisitDate() + "'", null);

        //Window Check List
        db.delete(CommonString.TABLE_BRAND_CHECK_LIST, CommonString.KEY_STORE_ID + "=" + jcp.getStoreId() + " AND " + CommonString.KEY_VISIT_DATE
                + "='" + jcp.getVisitDate() + "'", null);

        ContentValues values = new ContentValues();
        ContentValues values_window_checklist = new ContentValues();
        ContentValues values_brand = new ContentValues();
        ContentValues values_brand_checklist = new ContentValues();

        long id = 0, brand_key_id = 0;
        try {
            for (int i = 0; i < windowList.size(); i++) {
                values.put(CommonString.KEY_STORE_ID, jcp.getStoreId());
                values.put(CommonString.KEY_VISIT_DATE, jcp.getVisitDate());
                values.put(CommonString.KEY_EXIST, windowList.get(i).getAnswered_id());

                values.put(CommonString.KEY_WINDOW_ID, windowList.get(i).getWindowId());
                values.put(CommonString.KEY_WINDOW, windowList.get(i).getWindow());
                values.put(CommonString.KEY_REASON_ID, windowList.get(i).getNonExecutionReasonId());
                values.put(CommonString.KEY_IMAGE_LONGSHOT, windowList.get(i).getImg_long_shot());
                values.put(CommonString.KEY_IMAGE_CLOSEUP, windowList.get(i).getImg_close_up());

                id = db.insert(CommonString.TABLE_WINDOW_HEADER, null, values);


                ArrayList<ChecklistMaster> windowCheckList = hashMapListChildData.get(windowList.get(i));

                for (int j = 0; j < windowCheckList.size(); j++) {

                    values_window_checklist.put(CommonString.KEY_STORE_ID, jcp.getStoreId());
                    values_window_checklist.put(CommonString.KEY_VISIT_DATE, jcp.getVisitDate());
                    values_window_checklist.put(CommonString.KEY_COMMON_ID, id);
                    values_window_checklist.put(CommonString.KEY_CHECKLIST_ID, windowCheckList.get(j).getChecklistId());
                    values_window_checklist.put(CommonString.KEY_ANSWER_CD, windowCheckList.get(j).getAnswered_cd());
                    values_window_checklist.put(CommonString.KEY_CHECKLIST, windowCheckList.get(j).getChecklist());

                    db.insert(CommonString.TABLE_WINDOW_CHECK_LIST, null, values_window_checklist);
                }

                ArrayList<BrandMaster> brandList = windowList.get(i).getBrandList();
                HashMap<BrandMaster, ArrayList<ChecklistMaster>> brandCheckList = windowList.get(i).getHashMapListChildData();

                for (int k = 0; k < brandList.size(); k++) {

                    values_brand.put(CommonString.KEY_STORE_ID, jcp.getStoreId());
                    values_brand.put(CommonString.KEY_VISIT_DATE, jcp.getVisitDate());
                    values_brand.put(CommonString.KEY_COMMON_ID, id);
                    values_brand.put(CommonString.KEY_BRAND_ID, brandList.get(k).getBrandId());
                    values_brand.put(CommonString.KEY_BRAND, brandList.get(k).getBrand());
                    values_brand.put(CommonString.KEY_STOCK, brandList.get(k).getQuantity());

                    brand_key_id = db.insert(CommonString.TABLE_WINDOW_BRAND_LIST, null, values_brand);

                    ArrayList<ChecklistMaster> checkList = brandCheckList.get(brandList.get(k));

                    for (int l = 0; l < checkList.size(); l++) {

                        values_brand_checklist.put(CommonString.KEY_STORE_ID, jcp.getStoreId());
                        values_brand_checklist.put(CommonString.KEY_VISIT_DATE, jcp.getVisitDate());
                        values_brand_checklist.put(CommonString.KEY_COMMON_ID, brand_key_id);
                        values_brand_checklist.put(CommonString.KEY_CHECKLIST_ID, checkList.get(l).getChecklistId());
                        values_brand_checklist.put(CommonString.KEY_ANSWER_CD, checkList.get(l).getAnswered_cd());
                        values_brand_checklist.put(CommonString.KEY_CHECKLIST, checkList.get(l).getChecklist());

                        db.insert(CommonString.TABLE_BRAND_CHECK_LIST, null, values_brand_checklist);
                    }
                }

            }

            if (id > 0) {
                return id;
            } else {
                return 0;
            }
        } catch (Exception ex) {
            Log.d("Database Exception ", ex.toString());
            return 0;
        }
    }

    //insert Display Master
    public boolean insertDisplayMasterData(DisplayMasterGetterSetter displayMasterGetterSetter) {
        db.delete("Display_Master", null, null);
        ContentValues values = new ContentValues();
        List<DisplayMaster> data = displayMasterGetterSetter.getDisplayMaster();
        try {
            if (data.size() == 0) {
                return false;
            }
            for (int i = 0; i < data.size(); i++) {
                values.put("Display_Id", data.get(i).getDisplayId());
                values.put("Display", data.get(i).getDisplay());

                long id = db.insert("Display_Master", null, values);
                if (id == -1) {
                    throw new Exception();
                }
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.d("Database Exception  ", ex.toString());
            return false;
        }
    }

    //get Secondary Visibility Display List data
    public ArrayList<DisplayMaster> getSecondaryVisibilityDisplayData(int Store_Type_Id, int Store_Category_Id, String State_Id) {
        Log.d("Fetchecklidata->Start<-", "-");
        ArrayList<DisplayMaster> list = new ArrayList<>();
        Cursor dbcursor = null;
        try {

            dbcursor = db.rawQuery("SELECT DM.Display , DM.Display_Id FROM Mapping_Secondary_Visibility MSV INNER JOIN Display_Master DM ON MSV.Display_Id = DM.Display_Id " +
                    "WHERE MSV.Store_Type_Id=" + Store_Type_Id + " AND MSV.Store_Category_Id=" + Store_Category_Id + " AND MSV.State_Id='" + State_Id + "'", null);


            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    DisplayMaster ch = new DisplayMaster();
                    ch.setDisplayId(dbcursor.getInt(dbcursor.getColumnIndexOrThrow("Display_Id")));
                    ch.setDisplay(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Display")));
                    list.add(ch);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {
            Log.d("Exc get Display list!", e.toString());
            return list;
        }
        Log.d("Fetching check->Stop<", "-");
        return list;
    }

    //get Window List data
    public ArrayList<BrandMaster> getCTUBrandData(int Store_Id) {
        Log.d("Fetchecklidata->Start<-", "-");
        ArrayList<BrandMaster> list = new ArrayList<>();
        Cursor dbcursor = null;
        try {

            dbcursor = db.rawQuery("SELECT BM.Brand_Id, BM.Brand FROM Mapping_CTU M INNER JOIN Brand_Master BM ON M.Brand_Id = BM.Brand_Id  " +
                    "WHERE M.Store_Id=" + Store_Id + "", null);


            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    BrandMaster ch = new BrandMaster();
                    ch.setBrandId(dbcursor.getInt(dbcursor.getColumnIndexOrThrow("Brand_Id")));
                    ch.setBrand(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Brand")));
                    list.add(ch);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {
            Log.d("Exc get brand list!", e.toString());
            return list;
        }
        Log.d("Fetching check->Stop<", "-");
        return list;
    }

    //insert CTU Data
    public long insertCTUData(JourneyPlan jcp, ArrayList<BrandMaster> brandList, HashMap<BrandMaster, ArrayList<ChecklistMaster>> hashMapListChildData) {

        db.delete(CommonString.TABLE_CTU_BRAND_HEADER, CommonString.KEY_STORE_ID + "=" + jcp.getStoreId() + " AND " + CommonString.KEY_VISIT_DATE
                + "='" + jcp.getVisitDate() + "'", null);
        //Window Check List
        db.delete(CommonString.TABLE_CTU_BRAND_CHECK_LIST, CommonString.KEY_STORE_ID + "=" + jcp.getStoreId() + " AND " + CommonString.KEY_VISIT_DATE
                + "='" + jcp.getVisitDate() + "'", null);
        ContentValues values = new ContentValues();
        ContentValues values_brand_checklist = new ContentValues();


        long id = 0;
        try {
            for (int i = 0; i < brandList.size(); i++) {
                values.put(CommonString.KEY_STORE_ID, jcp.getStoreId());
                values.put(CommonString.KEY_VISIT_DATE, jcp.getVisitDate());
                values.put(CommonString.KEY_EXIST, brandList.get(i).getAnswered_id());

                values.put(CommonString.KEY_BRAND_ID, brandList.get(i).getBrandId());
                values.put(CommonString.KEY_BRAND, brandList.get(i).getBrand());
                values.put(CommonString.KEY_REASON_ID, brandList.get(i).getNonExecutionReasonId());
                values.put(CommonString.KEY_IMAGE_LONGSHOT, brandList.get(i).getImg_long_shot());
                values.put(CommonString.KEY_IMAGE_CLOSEUP, brandList.get(i).getImg_close_up());

                id = db.insert(CommonString.TABLE_CTU_BRAND_HEADER, null, values);

                ArrayList<ChecklistMaster> windowCheckList = hashMapListChildData.get(brandList.get(i));

                for (int j = 0; j < windowCheckList.size(); j++) {

                    values_brand_checklist.put(CommonString.KEY_STORE_ID, jcp.getStoreId());
                    values_brand_checklist.put(CommonString.KEY_VISIT_DATE, jcp.getVisitDate());
                    values_brand_checklist.put(CommonString.KEY_COMMON_ID, id);
                    values_brand_checklist.put(CommonString.KEY_CHECKLIST_ID, windowCheckList.get(j).getChecklistId());
                    values_brand_checklist.put(CommonString.KEY_ANSWER_CD, windowCheckList.get(j).getAnswered_cd());
                    values_brand_checklist.put(CommonString.KEY_CHECKLIST, windowCheckList.get(j).getChecklist());

                    db.insert(CommonString.TABLE_CTU_BRAND_CHECK_LIST, null, values_brand_checklist);
                }
            }

            if (id > 0) {
                return id;
            } else {
                return 0;
            }
        } catch (Exception ex) {
            Log.d("Database Exception ", ex.toString());
            return 0;
        }
    }

    //Insert secondary Window
    public long insertSecondaryVisibilityData(JourneyPlan jcp, ArrayList<DisplayMaster> displayList, HashMap<DisplayMaster, ArrayList<ChecklistMaster>> hashMapListChildData) {
        try {
            db.delete(CommonString.TABLE_SECONDARY_VISIBILITY_HEADER, CommonString.KEY_STORE_ID + "=" + jcp.getStoreId() + " AND " + CommonString.KEY_VISIT_DATE
                    + "='" + jcp.getVisitDate() + "'", null);
            //Window Check List
            db.delete(CommonString.TABLE_SECONDARY_VISIBILITY_CHECK_LIST, CommonString.KEY_STORE_ID + "=" + jcp.getStoreId() + " AND " + CommonString.KEY_VISIT_DATE
                    + "='" + jcp.getVisitDate() + "'", null);
            ContentValues values = new ContentValues();
            ContentValues values_brand_checklist = new ContentValues();


            long id = 0;

            for (int i = 0; i < displayList.size(); i++) {
                values.put(CommonString.KEY_STORE_ID, jcp.getStoreId());
                values.put(CommonString.KEY_VISIT_DATE, jcp.getVisitDate());
                values.put(CommonString.KEY_EXIST, displayList.get(i).getAnswered_id());
                values.put(CommonString.KEY_STOCK, displayList.get(i).getQuantity());
                values.put(CommonString.KEY_DISPLAY_ID, displayList.get(i).getDisplayId());
                values.put(CommonString.KEY_DISPLAY, displayList.get(i).getDisplay());
                values.put(CommonString.KEY_IMAGE_LONGSHOT, displayList.get(i).getImg_long_shot());
                values.put(CommonString.KEY_IMAGE_CLOSEUP, displayList.get(i).getImg_close_up());

                id = db.insert(CommonString.TABLE_SECONDARY_VISIBILITY_HEADER, null, values);

                ArrayList<ChecklistMaster> windowCheckList = hashMapListChildData.get(displayList.get(i));

                for (int j = 0; j < windowCheckList.size(); j++) {

                    values_brand_checklist.put(CommonString.KEY_STORE_ID, jcp.getStoreId());
                    values_brand_checklist.put(CommonString.KEY_VISIT_DATE, jcp.getVisitDate());
                    values_brand_checklist.put(CommonString.KEY_COMMON_ID, id);
                    values_brand_checklist.put(CommonString.KEY_CHECKLIST_ID, windowCheckList.get(j).getChecklistId());
                    values_brand_checklist.put(CommonString.KEY_ANSWER_CD, windowCheckList.get(j).getAnswered_cd());
                    values_brand_checklist.put(CommonString.KEY_CHECKLIST, windowCheckList.get(j).getChecklist());

                    db.insert(CommonString.TABLE_SECONDARY_VISIBILITY_CHECK_LIST, null, values_brand_checklist);
                }
            }

            if (id > 0) {
                return id;
            } else {
                return 0;
            }
        } catch (Exception ex) {
            Log.d("Database Exception ", ex.toString());
            return 0;
        }
    }

    //get wINDOW data
    public ArrayList<WindowMaster> getWindowDefaultData(int Store_Type_Id, int Store_Category_Id, String State_Id) {
        Log.d("Fetchecklidata->Start<-", "-");
        ArrayList<WindowMaster> list = new ArrayList<>();
        Cursor dbcursor = null;
        try {


            dbcursor = db.rawQuery("SELECT DISTINCT WM.Window_Id, WM.Window, WM.Window_Image FROM Mapping_Window M INNER JOIN Window_Master WM ON M.Window_Id = WM.Window_Id " +
                    "WHERE M.Store_Type_Id=" + Store_Type_Id + " AND M.Store_Category_Id=" + Store_Category_Id + " AND M.State_Id='" + State_Id + "'", null);


            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    WindowMaster ch = new WindowMaster();
                    ch.setWindowId(dbcursor.getInt(dbcursor.getColumnIndexOrThrow("Window_Id")));
                    ch.setWindow(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Window")));
                    ch.setWindow_Image_refrance(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Window_Image")));
                    list.add(ch);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {
            Log.d("Exc get Display list!", e.toString());
            return list;
        }
        Log.d("Fetching check->Stop<", "-");
        return list;
    }


    //--------------------------------Neeraj------------------------------

    public boolean insertSubCategoryMaster(SubCategoryMasterGetterSetter subCategoryMasterGetterSetter) {
        db.delete("Sub_Category_Master", null, null);
        ContentValues values = new ContentValues();
        List<SubCategoryMaster> data = subCategoryMasterGetterSetter.getSubCategoryMaster();
        try {
            if (data.size() == 0) {
                return false;
            }
            for (int i = 0; i < data.size(); i++) {
                values.put("Sub_Category_Id", data.get(i).getSubCategoryId());
                values.put("Sub_Category", data.get(i).getSubCategory());
                values.put("Category_Id", data.get(i).getCategoryId());
                values.put("Sub_Category_Sequence", data.get(i).getSubCategorySequence());
                long id = db.insert("Sub_Category_Master", null, values);
                if (id == -1) {
                    throw new Exception();
                }
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.d("Database Exception  ", ex.toString());
            return false;
        }
    }

    public ArrayList<MappingMenuChecklist> getCheckListId(String menu_id) {
        ArrayList<MappingMenuChecklist> list = new ArrayList<>();
        Cursor dbcursor = null;
        try {

            dbcursor = db.rawQuery("SELECT * FROM Mapping_Menu_Checklist WHERE Menu_Id = " + menu_id, null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    MappingMenuChecklist sb1 = new MappingMenuChecklist();
                    sb1.setChecklistId(dbcursor.getInt(dbcursor.getColumnIndexOrThrow("Checklist_Id")));
                    list.add(sb1);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {

            return list;
        }

        Log.d("Fetching non working", "-------------------");
        return list;
    }

    public ChecklistMaster getCheckListQuestions(Integer menuChecklistId) {
        ChecklistMaster sb1 = new ChecklistMaster();
        Cursor dbcursor = null;
        try {

            dbcursor = db.rawQuery("SELECT * FROM Checklist_Master WHERE Checklist_Id = " + menuChecklistId, null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    sb1.setChecklist(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Checklist")));
                    sb1.setChecklistId(dbcursor.getInt(dbcursor.getColumnIndexOrThrow("Checklist_Id")));
                    dbcursor.moveToNext();
                }
                dbcursor.close();
            }

        } catch (Exception e) {
            Log.d("Exception when fetching", e.toString());
            return sb1;
        }

        Log.d("Fetching non working", "-------------------");
        return sb1;
    }

    public ArrayList<ChecklistAnswer> getCheckListQuestionsAnswer(Integer checklistId) {
        ArrayList<ChecklistAnswer> list = new ArrayList<>();
        Cursor dbcursor = null;
        try {

            dbcursor = db.rawQuery("SELECT * FROM Checklist_Answer WHERE Checklist_Id = " + checklistId, null);
            final ChecklistAnswer sb = new ChecklistAnswer();
            sb.setAnswer("-Select Answer-");
            sb.setAnswerId(0);
            list.add(0, sb);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    ChecklistAnswer sb1 = new ChecklistAnswer();
                    sb1.setAnswer(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Answer")));
                    sb1.setAnswerId(dbcursor.getInt(dbcursor.getColumnIndexOrThrow("Answer_Id")));
                    list.add(sb1);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching", e.toString());
            return list;
        }

        Log.d("Fetching non working", "-------------------");
        return list;
    }

    public long insertFeedBackQuestionsData(String visit_date, ArrayList<ChecklistMaster> checklistQuestions, String store_id, String menu_id) {
        long id = 0;
        db.delete(CommonString.TABLE_FEEDBACK_QUESTIONS_DATA, CommonString.KEY_STORE_ID + "=" + store_id + " and " + CommonString.KEY_MENU_ID + " = " + menu_id + "", null);
        ContentValues values;
        try {

            for (int i = 0; i < checklistQuestions.size(); i++) {
                values = new ContentValues();
                values.put(CommonString.KEY_VISIT_DATE, visit_date);
                values.put(CommonString.KEY_MENU_ID, menu_id);
                values.put(CommonString.KEY_STORE_ID, store_id);
                values.put(CommonString.KEY_QUESTION, checklistQuestions.get(i).getChecklist());
                values.put(CommonString.KEY_QUESTION_ID, checklistQuestions.get(i).getChecklistId());
                values.put(CommonString.KEY_CORRECT_ANSWER_ID, checklistQuestions.get(i).getCorrectAnswer_Id());

                id = db.insert(CommonString.TABLE_FEEDBACK_QUESTIONS_DATA, null, values);
            }

            if (id > 0) {
                return id;
            } else {
                return 0;
            }

        } catch (Exception ex) {
            return 0;
        }
    }


    public ArrayList<ChecklistMaster> getSavedFeedBackData(String store_id, String menu_id, String visit_date) {
        ArrayList<ChecklistMaster> list = new ArrayList<>();
        Cursor dbcursor = null;
        try {

            dbcursor = db.rawQuery("SELECT * FROM  " + CommonString.TABLE_FEEDBACK_QUESTIONS_DATA + " WHERE " + CommonString.KEY_STORE_ID + " = '" + store_id + "' AND " + CommonString.KEY_MENU_ID + " = '" + menu_id + "' ", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    ChecklistMaster sb1 = new ChecklistMaster();
                    sb1.setCorrectAnswer_Id(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_CORRECT_ANSWER_ID)));
                    sb1.setChecklist(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_QUESTION)));
                    sb1.setChecklistId(dbcursor.getInt(dbcursor.getColumnIndexOrThrow(CommonString.KEY_QUESTION_ID)));
                    list.add(sb1);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {

            return list;
        }
        return list;
    }


    public boolean insertShareOfShelfMaster(MappingShareOfShelfGetterSetter mappingShareOfShelfGetterSetter) {
        db.delete("mapping_Share_Of_Shelf", null, null);
        ContentValues values = new ContentValues();
        List<MappingShareOfShelf> data = mappingShareOfShelfGetterSetter.getMappingShareOfShelf();
        try {
            if (data.size() == 0) {
                return false;
            }

            for (int i = 0; i < data.size(); i++) {
                values.put("Brand_Id", data.get(i).getBrandId());
                long id = db.insert("mapping_Share_Of_Shelf", null, values);
                if (id == -1) {
                    throw new Exception();
                }
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.d("Database Exception  ", ex.toString());
            return false;
        }
    }

    public boolean insertMappingSampling(MappingSamplingGetterSetter mappingSamplingGetterSetter) {
        db.delete("Mapping_Sampling", null, null);
        ContentValues values = new ContentValues();
        List<MappingSampling> data = mappingSamplingGetterSetter.getMappingSampling();
        try {
            if (data.size() == 0) {
                return false;
            }

            for (int i = 0; i < data.size(); i++) {
                values.put("Sku_Id", data.get(i).getSkuId());
                values.put("Store_id", data.get(i).getStoreId());

                long id = db.insert("Mapping_Sampling", null, values);
                if (id == -1) {
                    throw new Exception();
                }
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.d("Database Exception  ", ex.toString());
            return false;
        }
    }

    public boolean insertMappingPaidVisibility(MappingPaidVisibilityGetterSetter mappingPaidVisibilityGetterSetter) {
        db.delete("Mapping_Paid_Visibility", null, null);
        ContentValues values = new ContentValues();
        List<MappingPaidVisibility> data = mappingPaidVisibilityGetterSetter.getMappingPaidVisibility();
        try {
            if (data.size() == 0) {
                return false;
            }

            for (int i = 0; i < data.size(); i++) {
                values.put("Brand_Id", data.get(i).getBrandId());
                values.put("Store_id", data.get(i).getStoreId());
                values.put("Display_Id", data.get(i).getDisplayId());

                long id = db.insert("Mapping_Paid_Visibility", null, values);
                if (id == -1) {
                    throw new Exception();
                }
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public ArrayList<CategoryMaster> getSOSCategoryMasterData(JourneyPlan journeyPlan) {
        ArrayList<CategoryMaster> list = new ArrayList<>();
        Cursor dbcursor = null;
        try {

            dbcursor = db.rawQuery("select distinct ca.Category_Id, ca.Category FROM  mapping_Share_Of_Shelf sos " +
                    "inner join Brand_Master bm ON sos.Brand_Id = bm.Brand_Id " +
                    "inner join Sub_Category_Master sb on bm.Sub_Category_Id = sb.Sub_Category_Id " +
                    "inner join Category_Master ca on sb.Category_Id = ca.Category_Id " +
                    "where sos.Store_Category_Id = '" + journeyPlan.getStoreCategoryId() + "' " +
                    "and sos.Store_Type_Id='" + journeyPlan.getStoreTypeId() + "' " +
                    "and sos.State_Id = '" + journeyPlan.getStateId() + "' ", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    CategoryMaster sb1 = new CategoryMaster();
                    sb1.setCategory(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Category")));
                    sb1.setCategoryId(dbcursor.getInt(dbcursor.getColumnIndexOrThrow("Category_Id")));
                    list.add(sb1);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {

            return list;
        }

        return list;
    }

    public ArrayList<CategoryMaster> getSOSBrandData(Integer categoryId) {
        ArrayList<CategoryMaster> list = new ArrayList<>();
        Cursor dbcursor = null;
        try {

            dbcursor = db.rawQuery("select distinct  bm.Brand,bm.Brand_Id from Sub_Category_Master cm " +
                    "inner join Brand_Master bm on cm.Sub_Category_Id = bm.Sub_Category_Id " +
                    "where cm.Category_Id = '" + categoryId + "' ", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    CategoryMaster sb1 = new CategoryMaster();
                    sb1.setBrand(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Brand")));
                    sb1.setBrand_Id(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Brand_Id")));
                    list.add(sb1);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching", e.toString());
            return list;
        }

        Log.d("Fetching non working", "-------------------");
        return list;
    }

    public long insertSOSCompleteData(HashMap<CategoryMaster, List<CategoryMaster>> listDataChild, List<CategoryMaster> listDataHeader, String store_id, String menu_id, String visit_date, String username) {
        long l = 0, common_id = 0, l2 = 0;
        db.delete(CommonString.TABLE_SOS_CHECKLIST_QUESTIONS_DATA, "STORE_ID ='" + store_id + "'", null);
        db.delete(CommonString.TABLE_SOS_HEADER_DATA, "STORE_ID ='" + store_id + "'", null);
        db.delete(CommonString.TABLE_SOS_CHILD_DATA, "STORE_ID ='" + store_id + "'", null);
        ContentValues values;
        ContentValues values1, values2;
        try {

            for (int i = 0; i < listDataHeader.size(); i++) {
                values = new ContentValues();
                values.put(CommonString.KEY_VISIT_DATE, visit_date);
                values.put(CommonString.KEY_USER_ID, username);
                values.put(CommonString.KEY_STORE_ID, store_id);
                values.put(CommonString.KEY_MENU_ID, menu_id);
                values.put(CommonString.KEY_CATEGORY_ID, listDataHeader.get(i).getCategoryId());
                values.put(CommonString.KEY_CATEGORY, listDataHeader.get(i).getCategory());
                values.put(CommonString.KEY_CATEGORY_FACING, listDataHeader.get(i).getCategory_Facing());
                values.put(CommonString.KEY_CATEGORY_IMAGE, listDataHeader.get(i).getCategory_Image());
                values.put(CommonString.KEY_SOS_PERCENTAGE, listDataHeader.get(i).getPercentage());


                common_id = db.insert(CommonString.TABLE_SOS_HEADER_DATA, null, values);
                if (common_id > 0) {

                    for (int j = 0; j < listDataChild.get(listDataHeader.get(i)).size(); j++) {
                        values1 = new ContentValues();
                        values1.put(CommonString.KEY_COMMON_ID, common_id);
                        values1.put(CommonString.KEY_VISIT_DATE, visit_date);
                        values1.put(CommonString.KEY_USER_ID, username);
                        values1.put(CommonString.KEY_STORE_ID, store_id);
                        values1.put(CommonString.KEY_MENU_ID, menu_id);
                        values1.put(CommonString.KEY_STORE_ID, store_id);
                        values1.put(CommonString.KEY_CATEGORY_ID, listDataHeader.get(i).getCategoryId());
                        values1.put(CommonString.KEY_BRAND_ID, listDataChild.get(listDataHeader.get(i)).get(j).getBrand_Id());
                        values1.put(CommonString.KEY_BRAND, listDataChild.get(listDataHeader.get(i)).get(j).getBrand());
                        values1.put(CommonString.KEY_BRAND_FACING, listDataChild.get(listDataHeader.get(i)).get(j).getBrand_Facing());

                        l = db.insert(CommonString.TABLE_SOS_CHILD_DATA, null, values1);
                        if (l > 0) {
                            for (int k = 0; k < listDataChild.get(listDataHeader.get(i)).get(j).getChecklistQuestions().size(); k++) {
                                values2 = new ContentValues();
                                values2.put(CommonString.KEY_COMMON_ID, l);
                                values2.put(CommonString.KEY_VISIT_DATE, visit_date);
                                values2.put(CommonString.KEY_USER_ID, username);
                                values2.put(CommonString.KEY_STORE_ID, store_id);
                                values2.put(CommonString.KEY_MENU_ID, menu_id);
                                values2.put(CommonString.KEY_CATEGORY_ID, listDataChild.get(listDataHeader.get(i)).get(j).getChecklistQuestions().get(k).getCategory_Id());
                                values2.put(CommonString.KEY_BRAND_ID, listDataChild.get(listDataHeader.get(i)).get(j).getChecklistQuestions().get(k).getBrand_Id());
                                values2.put(CommonString.KEY_QUESTION_ID, listDataChild.get(listDataHeader.get(i)).get(j).getChecklistQuestions().get(k).getChecklistId());
                                values2.put(CommonString.KEY_QUESTION, listDataChild.get(listDataHeader.get(i)).get(j).getChecklistQuestions().get(k).getChecklist());
                                values2.put(CommonString.KEY_CORRECT_ANSWER_ID, listDataChild.get(listDataHeader.get(i)).get(j).getChecklistQuestions().get(k).getCorrectAnswer_Id());

                                l2 = db.insert(CommonString.TABLE_SOS_CHECKLIST_QUESTIONS_DATA, null, values2);
                            }
                        }
                    }

                }
            }

            if (common_id > 0) {
                return common_id;
            } else {
                return 0;
            }
        } catch (Exception ex) {

            return 0;
        }
    }


    public List<CategoryMaster> getSavedSOSHeaderData(String store_id, String visit_date) {
        ArrayList<CategoryMaster> list = new ArrayList<>();
        Cursor dbcursor = null;
        try {

            dbcursor = db.rawQuery("select * from " + CommonString.TABLE_SOS_HEADER_DATA + " where " + CommonString.KEY_STORE_ID + " = '" + store_id + "' and " + CommonString.KEY_VISIT_DATE + " = '" + visit_date + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    CategoryMaster sb1 = new CategoryMaster();
                    sb1.setCategory(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_CATEGORY)));
                    sb1.setCategoryId(dbcursor.getInt(dbcursor.getColumnIndexOrThrow(CommonString.KEY_CATEGORY_ID)));
                    sb1.setCategory_Facing(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_CATEGORY_FACING)));
                    sb1.setCategory_Image(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_CATEGORY_IMAGE)));
                    sb1.setStore_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
                    sb1.setMenu_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_MENU_ID)));
                    sb1.setPercentage(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_SOS_PERCENTAGE)));
                    list.add(sb1);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching", e.toString());
            return list;
        }

        Log.d("Fetching non working", "-------------------");
        return list;
    }

    public ArrayList<CategoryMaster> getSavedSOSInsertedChildData(Integer categoryId, String store_id, String visit_date) {
        ArrayList<CategoryMaster> list = new ArrayList<>();
        Cursor dbcursor = null, dbcursor2 = null;
        String brand_id = "";
        try {

            dbcursor = db.rawQuery("select * from " + CommonString.TABLE_SOS_CHILD_DATA + " where " + CommonString.KEY_STORE_ID + " = '" + store_id + "' and " + CommonString.KEY_VISIT_DATE + " = '" + visit_date + "' and " + CommonString.KEY_CATEGORY_ID + " = '" + categoryId + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    CategoryMaster sb1 = new CategoryMaster();
                    brand_id = dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_BRAND_ID));
                    sb1.setBrand(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_BRAND)));
                    sb1.setBrand_Id(brand_id);
                    sb1.setBrand_Facing(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_BRAND_FACING)));

                    ArrayList<ChecklistMaster> list2 = new ArrayList<>();
                    dbcursor2 = db.rawQuery("select * from " + CommonString.TABLE_SOS_CHECKLIST_QUESTIONS_DATA + " where " + CommonString.KEY_STORE_ID + " = '" + store_id + "' and " + CommonString.KEY_VISIT_DATE + " = '" + visit_date + "' and " + CommonString.KEY_CATEGORY_ID + " = '" + categoryId + "' and " + CommonString.KEY_BRAND_ID + " = '" + brand_id + "'", null);

                    if (dbcursor2 != null) {
                        dbcursor2.moveToFirst();
                        while (!dbcursor2.isAfterLast()) {
                            ChecklistMaster chcekList = new ChecklistMaster();
                            chcekList.setCategory_Id(dbcursor2.getString(dbcursor2.getColumnIndexOrThrow(CommonString.KEY_CATEGORY_ID)));
                            chcekList.setBrand_Id(dbcursor2.getString(dbcursor2.getColumnIndexOrThrow(CommonString.KEY_BRAND_ID)));
                            chcekList.setChecklist(dbcursor2.getString(dbcursor2.getColumnIndexOrThrow(CommonString.KEY_QUESTION)));
                            chcekList.setChecklistId(dbcursor2.getInt(dbcursor2.getColumnIndexOrThrow(CommonString.KEY_QUESTION_ID)));
                            chcekList.setCorrectAnswer_Id(dbcursor2.getString(dbcursor2.getColumnIndexOrThrow(CommonString.KEY_CORRECT_ANSWER_ID)));
                            list2.add(chcekList);
                            dbcursor2.moveToNext();
                        }
                        dbcursor2.close();
                    }

                    sb1.setChecklistQuestions(list2);
                    list.add(sb1);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching", e.toString());
            return list;
        }

        Log.d("Fetching non working", "-------------------");
        return list;
    }

    //get Window Data after insertion
    public ArrayList<WindowMaster> getWindowInsertedData(int storeId, String visitDate) {
        Log.d("Fetching", "Storedata--------------->Start<------------");
        ArrayList<WindowMaster> list = new ArrayList<>();
        Cursor dbcursor = null, dbcursor2 = null, dbcursor3 = null, dbcursor4 = null;

        try {
            dbcursor = db.rawQuery("SELECT * FROM WINDOW_HEADER WHERE STORE_ID ='" + storeId + "' AND VISIT_DATE='" + visitDate + "' ", null);


            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    WindowMaster sb = new WindowMaster();
                    sb.setKey_Id(String.valueOf(dbcursor.getInt(dbcursor.getColumnIndexOrThrow(CommonString.KEY_ID))));
                    sb.setWindow(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_WINDOW)));
                    sb.setWindowId((dbcursor.getInt(dbcursor.getColumnIndexOrThrow(CommonString.KEY_WINDOW_ID))));
                    sb.setAnswered_id(dbcursor.getInt(dbcursor.getColumnIndexOrThrow(CommonString.KEY_EXIST)));
                    sb.setNonExecutionReasonId(dbcursor.getInt(dbcursor.getColumnIndexOrThrow(CommonString.KEY_REASON_ID)));
                    sb.setImg_close_up(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_IMAGE_LONGSHOT)));
                    sb.setImg_long_shot(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_IMAGE_CLOSEUP)));

                    dbcursor2 = db.rawQuery("SELECT * FROM " + CommonString.TABLE_WINDOW_BRAND_LIST + " WHERE " + CommonString.KEY_COMMON_ID + " ='" + sb.getKey_Id() + "'", null);

                    ArrayList<BrandMaster> brandList = new ArrayList<>();
                    HashMap<BrandMaster, ArrayList<ChecklistMaster>> hashMapListChildData = new HashMap<>();

                    if (dbcursor2 != null) {
                        dbcursor2.moveToFirst();
                        while (!dbcursor2.isAfterLast()) {
                            BrandMaster brand = new BrandMaster();
                            brand.setKey_Id(dbcursor2.getInt(dbcursor2.getColumnIndexOrThrow(CommonString.KEY_ID)));
                            brand.setBrandId(dbcursor2.getInt(dbcursor2.getColumnIndexOrThrow(CommonString.KEY_BRAND_ID)));
                            brand.setBrand((dbcursor2.getString(dbcursor2.getColumnIndexOrThrow(CommonString.KEY_BRAND))));
                            brand.setQuantity(dbcursor2.getString(dbcursor2.getColumnIndexOrThrow(CommonString.KEY_STOCK)));

                            brandList.add(brand);

                            dbcursor3 = db.rawQuery("SELECT * FROM " + CommonString.TABLE_BRAND_CHECK_LIST + " WHERE " + CommonString.KEY_COMMON_ID + " ='" + brand.getKey_Id() + "'", null);

                            ArrayList<ChecklistMaster> checkList = new ArrayList<>();

                            if (dbcursor3 != null) {
                                dbcursor3.moveToFirst();
                                while (!dbcursor3.isAfterLast()) {
                                    ChecklistMaster checklist = new ChecklistMaster();
                                    checklist.setChecklistId(dbcursor3.getInt(dbcursor3.getColumnIndexOrThrow(CommonString.KEY_CHECKLIST_ID)));
                                    checklist.setChecklist((dbcursor3.getString(dbcursor3.getColumnIndexOrThrow(CommonString.KEY_CHECKLIST))));
                                    checklist.setAnswered_cd(dbcursor3.getInt(dbcursor3.getColumnIndexOrThrow(CommonString.KEY_ANSWER_CD)));

                                    ArrayList<ChecklistAnswer> answerList = new ArrayList<>();
                                    ChecklistAnswer reason1 = new ChecklistAnswer();

                                    reason1.setAnswerId(0);
                                    reason1.setAnswer("Select Reason");
                                    answerList.add(reason1);

                                    dbcursor4 = db.rawQuery("select * from Checklist_Answer where checklist_Id ='" + checklist.getChecklistId() + "' ", null);

                                    if (dbcursor4 != null) {
                                        dbcursor4.moveToFirst();
                                        while (!dbcursor4.isAfterLast()) {
                                            ChecklistAnswer reason = new ChecklistAnswer();
                                            reason.setAnswer(dbcursor4.getString(dbcursor4.getColumnIndexOrThrow("Answer")));
                                            reason.setAnswerId(Integer.valueOf(dbcursor4.getString(dbcursor4.getColumnIndexOrThrow("Answer_Id"))));
                                            reason.setChecklistId(Integer.valueOf(dbcursor4.getString(dbcursor4.getColumnIndexOrThrow("Checklist_Id"))));

                                            answerList.add(reason);
                                            dbcursor4.moveToNext();
                                        }
                                        dbcursor4.close();
                                        //return list;
                                    }

                                    checklist.setCheckListAnswer(answerList);
                                    checkList.add(checklist);

                                    dbcursor3.moveToNext();
                                }
                                dbcursor3.close();
                                //return list;
                                //sb.setBrandList(brandList);
                                hashMapListChildData.put(brand, checkList);
                            }

                            dbcursor2.moveToNext();
                        }
                        dbcursor2.close();
                        //return list;
                        sb.setBrandList(brandList);
                        sb.setHashMapListChildData(hashMapListChildData);
                    }

                    list.add(sb);
                    dbcursor.moveToNext();

                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {

            return list;
        }

        return list;
    }

    //getWindow Checklist inserted data
    public ArrayList<ChecklistMaster> getWindowCheckListInsertedData(String CommonId) {
        ArrayList<ChecklistMaster> list = new ArrayList<>();
        Cursor dbcursor = null;
        try {

            dbcursor = db.rawQuery("SELECT * FROM  " + CommonString.TABLE_WINDOW_CHECK_LIST + " WHERE " + CommonString.KEY_COMMON_ID + " = '" + CommonId + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {

                    ChecklistMaster checklist = new ChecklistMaster();
                    checklist.setChecklistId(dbcursor.getInt(dbcursor.getColumnIndexOrThrow(CommonString.KEY_CHECKLIST_ID)));
                    checklist.setChecklist((dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_CHECKLIST))));
                    checklist.setAnswered_cd(dbcursor.getInt(dbcursor.getColumnIndexOrThrow(CommonString.KEY_ANSWER_CD)));

                    list.add(checklist);

                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching", e.toString());
            return list;
        }

        Log.d("Fetching non working", "-------------------");
        return list;
    }

    //get CTU inserted data
    public ArrayList<BrandMaster> getCTUInsertedData(String storeId, String visitDate) {

        ArrayList<BrandMaster> brandList = new ArrayList<>();

        Cursor dbcursor2 = null;
        try {

            dbcursor2 = db.rawQuery("SELECT * FROM  " + CommonString.TABLE_CTU_BRAND_HEADER + " WHERE STORE_ID ='" + storeId + "' AND VISIT_DATE='" + visitDate + "' ", null);

            if (dbcursor2 != null) {
                dbcursor2.moveToFirst();
                while (!dbcursor2.isAfterLast()) {
                    BrandMaster brand = new BrandMaster();
                    brand.setKey_Id(dbcursor2.getInt(dbcursor2.getColumnIndexOrThrow(CommonString.KEY_ID)));
                    brand.setBrandId(dbcursor2.getInt(dbcursor2.getColumnIndexOrThrow(CommonString.KEY_BRAND_ID)));
                    brand.setBrand((dbcursor2.getString(dbcursor2.getColumnIndexOrThrow(CommonString.KEY_BRAND))));
                    brand.setAnswered_id(dbcursor2.getInt(dbcursor2.getColumnIndexOrThrow(CommonString.KEY_EXIST)));
                    brand.setNonExecutionReasonId(dbcursor2.getInt(dbcursor2.getColumnIndexOrThrow(CommonString.KEY_REASON_ID)));
                    brand.setImg_long_shot(dbcursor2.getString(dbcursor2.getColumnIndexOrThrow(CommonString.KEY_IMAGE_LONGSHOT)));
                    brand.setImg_close_up(dbcursor2.getString(dbcursor2.getColumnIndexOrThrow(CommonString.KEY_IMAGE_CLOSEUP)));

                    brandList.add(brand);

                    dbcursor2.moveToNext();
                }
                dbcursor2.close();
                return brandList;

            }
        } catch (Exception e) {
            Log.d("Excep when fetching CTU", e.toString());
            return brandList;
        }

        Log.d("Fetching CTU", "-------------------");
        return brandList;
    }

    //get CTU Checklist inserted data
    public ArrayList<ChecklistMaster> getCTUCheckListInsertedData(BrandMaster brand) {
        ArrayList<ChecklistMaster> checkList = new ArrayList<>();
        Cursor dbcursor3 = null, dbcursor4 = null;
        try {

            dbcursor3 = db.rawQuery("SELECT * FROM " + CommonString.TABLE_CTU_BRAND_CHECK_LIST + " WHERE " + CommonString.KEY_COMMON_ID + " ='" + brand.getKey_Id() + "'", null);

            if (dbcursor3 != null) {
                dbcursor3.moveToFirst();
                while (!dbcursor3.isAfterLast()) {
                    ChecklistMaster checklist = new ChecklistMaster();
                    checklist.setChecklistId(dbcursor3.getInt(dbcursor3.getColumnIndexOrThrow(CommonString.KEY_CHECKLIST_ID)));
                    checklist.setChecklist((dbcursor3.getString(dbcursor3.getColumnIndexOrThrow(CommonString.KEY_CHECKLIST))));
                    checklist.setAnswered_cd(dbcursor3.getInt(dbcursor3.getColumnIndexOrThrow(CommonString.KEY_ANSWER_CD)));

                    ArrayList<ChecklistAnswer> answerList = new ArrayList<>();
                    ChecklistAnswer reason1 = new ChecklistAnswer();

                    reason1.setAnswerId(0);
                    reason1.setAnswer("Select Reason");
                    answerList.add(reason1);

                    dbcursor4 = db.rawQuery("select * from Checklist_Answer where checklist_Id ='" + checklist.getChecklistId() + "' ", null);

                    if (dbcursor4 != null) {
                        dbcursor4.moveToFirst();
                        while (!dbcursor4.isAfterLast()) {
                            ChecklistAnswer reason = new ChecklistAnswer();
                            reason.setAnswer(dbcursor4.getString(dbcursor4.getColumnIndexOrThrow("Answer")));
                            reason.setAnswerId(Integer.valueOf(dbcursor4.getString(dbcursor4.getColumnIndexOrThrow("Answer_Id"))));
                            reason.setChecklistId(Integer.valueOf(dbcursor4.getString(dbcursor4.getColumnIndexOrThrow("Checklist_Id"))));

                            answerList.add(reason);
                            dbcursor4.moveToNext();
                        }
                        dbcursor4.close();
                        //return list;
                    }

                    checklist.setCheckListAnswer(answerList);
                    checkList.add(checklist);

                    dbcursor3.moveToNext();
                }
                dbcursor3.close();
                return checkList;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching", e.toString());
            return checkList;
        }

        Log.d("Fetching CTU CH", "-------------------");
        return checkList;
    }

    //get Secondary Visibility inserted data
    public ArrayList<DisplayMaster> getSecondaryVisibilityInsertedData(String storeId, String visitDate) {

        ArrayList<DisplayMaster> displayList = new ArrayList<>();

        Cursor dbcursor2 = null, dbcursor3 = null, dbcursor4 = null;
        try {

            dbcursor2 = db.rawQuery("SELECT * FROM  " + CommonString.TABLE_SECONDARY_VISIBILITY_HEADER + " WHERE STORE_ID ='" + storeId + "' AND VISIT_DATE='" + visitDate + "' ", null);


            if (dbcursor2 != null) {
                dbcursor2.moveToFirst();
                while (!dbcursor2.isAfterLast()) {
                    DisplayMaster display = new DisplayMaster();
                    display.setKey_Id(dbcursor2.getInt(dbcursor2.getColumnIndexOrThrow(CommonString.KEY_ID)));
                    display.setAnswered_id(dbcursor2.getInt(dbcursor2.getColumnIndexOrThrow(CommonString.KEY_EXIST)));
                    display.setDisplayId(dbcursor2.getInt(dbcursor2.getColumnIndexOrThrow(CommonString.KEY_DISPLAY_ID)));
                    display.setDisplay((dbcursor2.getString(dbcursor2.getColumnIndexOrThrow(CommonString.KEY_DISPLAY))));
                    display.setImg_long_shot(dbcursor2.getString(dbcursor2.getColumnIndexOrThrow(CommonString.KEY_IMAGE_LONGSHOT)));
                    display.setImg_close_up(dbcursor2.getString(dbcursor2.getColumnIndexOrThrow(CommonString.KEY_IMAGE_CLOSEUP)));
                    display.setQuantity(dbcursor2.getString(dbcursor2.getColumnIndexOrThrow(CommonString.KEY_STOCK)));

                    displayList.add(display);

                    dbcursor2.moveToNext();
                }
                dbcursor2.close();
                return displayList;

            }
        } catch (Exception e) {
            Log.d("Excep fetch Secondary", e.toString());
            return displayList;
        }

        Log.d("Fetching Secondary", "-------------------");
        return displayList;
    }

    //get Secondary Visibility Checklist inserted data
    public ArrayList<ChecklistMaster> getSecondaryVisibilityCheckListInsertedData(DisplayMaster display) {
        ArrayList<ChecklistMaster> checkList = new ArrayList<>();
        Cursor dbcursor3 = null, dbcursor4 = null;
        try {

            dbcursor3 = db.rawQuery("SELECT * FROM " + CommonString.TABLE_SECONDARY_VISIBILITY_CHECK_LIST + " WHERE " + CommonString.KEY_COMMON_ID + " ='" + display.getKey_Id() + "'", null);

            if (dbcursor3 != null) {
                dbcursor3.moveToFirst();
                while (!dbcursor3.isAfterLast()) {
                    ChecklistMaster checklist = new ChecklistMaster();
                    checklist.setChecklistId(dbcursor3.getInt(dbcursor3.getColumnIndexOrThrow(CommonString.KEY_CHECKLIST_ID)));
                    checklist.setChecklist((dbcursor3.getString(dbcursor3.getColumnIndexOrThrow(CommonString.KEY_CHECKLIST))));
                    checklist.setAnswered_cd(dbcursor3.getInt(dbcursor3.getColumnIndexOrThrow(CommonString.KEY_ANSWER_CD)));

                    ArrayList<ChecklistAnswer> answerList = new ArrayList<>();
                    ChecklistAnswer reason1 = new ChecklistAnswer();

                    reason1.setAnswerId(0);
                    reason1.setAnswer("Select Reason");
                    answerList.add(reason1);

                    dbcursor4 = db.rawQuery("select * from Checklist_Answer where checklist_Id ='" + checklist.getChecklistId() + "' ", null);

                    if (dbcursor4 != null) {
                        dbcursor4.moveToFirst();
                        while (!dbcursor4.isAfterLast()) {
                            ChecklistAnswer reason = new ChecklistAnswer();
                            reason.setAnswer(dbcursor4.getString(dbcursor4.getColumnIndexOrThrow("Answer")));
                            reason.setAnswerId(Integer.valueOf(dbcursor4.getString(dbcursor4.getColumnIndexOrThrow("Answer_Id"))));
                            reason.setChecklistId(Integer.valueOf(dbcursor4.getString(dbcursor4.getColumnIndexOrThrow("Checklist_Id"))));

                            answerList.add(reason);
                            dbcursor4.moveToNext();
                        }
                        dbcursor4.close();
                        //return list;
                    }

                    checklist.setCheckListAnswer(answerList);
                    checkList.add(checklist);

                    dbcursor3.moveToNext();
                }
                dbcursor3.close();
                return checkList;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching", e.toString());
            return checkList;
        }

        Log.d("Fetching CTU CH", "-------------------");
        return checkList;
    }

    //marico
    //Insert Header Data with img
    public void InsertHeaderOpeningStocklistData(JourneyPlan jcp, List<StockNewGetterSetter> save_listDataHeader) {

        ContentValues values = new ContentValues();
        try {
            db.beginTransaction();

            for (int i = 0; i < save_listDataHeader.size(); i++) {
                values.put("STORE_ID", jcp.getStoreId());
                values.put("VISIT_DATE", jcp.getVisitDate());
                values.put("CATEGORY_CD", save_listDataHeader.get(i).getCategory_cd());
                values.put("CATEGORY", save_listDataHeader.get(i).getCategory());
                values.put("IMAGE_CAT_ONE", save_listDataHeader.get(i).getImg_cat_one());


                long l = db.insert(CommonString.TABLE_STOCK_IMAGE, null, values);
            }
            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (Exception ex) {

        }
    }


    //Insert Opening Data with Brand
    public void InsertOpeningStocklistData(JourneyPlan jcp,
                                           HashMap<StockNewGetterSetter, List<StockNewGetterSetter>> data, List<StockNewGetterSetter> save_listDataHeader) {
        ContentValues values = new ContentValues();
        ContentValues values1 = new ContentValues();

        try {
            db.beginTransaction();
            for (int i = 0; i < save_listDataHeader.size(); i++) {
                values.put("STORE_ID", jcp.getStoreId());
                values.put("CATEGORY_CD", save_listDataHeader.get(i).getCategory_cd());
                values.put("CATEGORY", save_listDataHeader.get(i).getCategory());
                values1.put("BRAND_CD", save_listDataHeader.get(i).getBrand_cd());
                values1.put("BRAND", save_listDataHeader.get(i).getBrand());
                long l = db.insert(CommonString.TABLE_INSERT_OPENINGHEADER_DATA, null, values);
                for (int j = 0; j < data.get(save_listDataHeader.get(i)).size(); j++) {
                    values1.put("Common_Id", (int) l);
                    values1.put("STORE_ID", jcp.getStoreId());
                    values1.put("CATEGORY_CD", save_listDataHeader.get(i).getCategory_cd());
                    values1.put("CATEGORY", save_listDataHeader.get(i).getCategory());
                    values1.put("BRAND_CD", save_listDataHeader.get(i).getBrand_cd());
                    values1.put("BRAND", save_listDataHeader.get(i).getBrand());
                    values1.put("SKU", data.get(save_listDataHeader.get(i)).get(j).getSku());
                    values1.put("SKU_CD", Integer.parseInt(data.get(save_listDataHeader.get(i)).get(j).getSku_cd()));
                    values1.put("OPENING_STOCK", data.get(save_listDataHeader.get(i)).get(j).getStock1());
                    values1.put("FOCUS", data.get(save_listDataHeader.get(i)).get(j).getFocus());


                    db.insert(CommonString.TABLE_STOCK_DATA, null, values1);
                }
            }
            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (Exception ex) {
        }
    }


    //Update Opening Data with Brand
    public void UpdateHeaderOpeningStocklistData(JourneyPlan jcp, List<StockNewGetterSetter> save_listDataHeader) {

        ContentValues values = new ContentValues();
        try {
            db.beginTransaction();

            for (int i = 0; i < save_listDataHeader.size(); i++) {
               // values.put("IMAGE_STK", save_listDataHeader.get(i).getImg_cam());
                values.put("IMAGE_CAT_ONE", save_listDataHeader.get(i).getImg_cat_one());
               // values.put("CAT_STOCK", save_listDataHeader.get(i).getCatstock());

                db.update(CommonString.TABLE_STOCK_IMAGE, values, "STORE_ID" + "='" + jcp.getStoreId() +
                        "' AND CATEGORY_CD " + "='" + Integer.parseInt(save_listDataHeader.get(i).getCategory_cd()) +
                        "' AND VISIT_DATE  ='" + jcp.getVisitDate() + "'", null);
            }
            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (Exception ex) {

        }
    }

    //Update Opening Data with Brand
    public void UpdateOpeningStocklistData(JourneyPlan jcp, HashMap<StockNewGetterSetter, List<StockNewGetterSetter>> data,
                                           List<StockNewGetterSetter> save_listDataHeader) {

        ContentValues values1 = new ContentValues();
        try {
            ArrayList<HeaderGetterSetter> list = new ArrayList<>();
            list = getHeaderStock(jcp.getStoreId());

            db.beginTransaction();
            for (int i = 0; i < list.size(); i++) {

                for (int j = 0; j < data.get(save_listDataHeader.get(i)).size(); j++) {
                 //   values1.put("OPENING_FACING", data.get(save_listDataHeader.get(i)).get(j).getEd_openingFacing());
                   // values1.put("OPENING_STOCK", data.get(save_listDataHeader.get(i)).get(j).getEd_openingFacing());
                    values1.put("OPENING_STOCK", data.get(save_listDataHeader.get(i)).get(j).getStock1());
                 //   values1.put("STOCK_1", data.get(save_listDataHeader.get(i)).get(j).getStock1());
                  //  values1.put("STOCK_2", data.get(save_listDataHeader.get(i)).get(j).getStock2());

                    db.update(CommonString.TABLE_STOCK_DATA, values1,
                            "Common_Id" + "='" + Integer.parseInt(list.get(i).getKeyId()) + "' AND SKU_CD " +
                                    "='" + Integer.parseInt(data.get(save_listDataHeader.get(i)).get(j).getSku_cd()) + "'", null);
                }
            }
            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (Exception ex) {

        }
    }

    //Header Key data
    public ArrayList<HeaderGetterSetter> getHeaderStock(int storeId) {
        ArrayList<HeaderGetterSetter> list = new ArrayList<HeaderGetterSetter>();
        Cursor dbcursor = null;

        try {

            dbcursor = db.rawQuery("SELECT KEY_ID FROM openingHeader_data WHERE STORE_ID= '" + storeId + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    HeaderGetterSetter sb = new HeaderGetterSetter();
                    sb.setKeyId(dbcursor.getString(dbcursor.getColumnIndexOrThrow("KEY_ID")));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            return list;
        }
        return list;
    }

    public boolean checkStock(String storeId) {
        Log.d("Fetching", "Opening Stock data--------------->Start<------------");
        ArrayList<StockNewGetterSetter> list = new ArrayList<StockNewGetterSetter>();
        Cursor dbcursor = null;

        try {

            dbcursor = db.rawQuery("SELECT DISTINCT SD.SKU_CD " +
                    "FROM openingHeader_data CD " +
                    "INNER JOIN STOCK_DATA SD " +
                    "ON CD.KEY_ID=SD.Common_Id " +
                    "WHERE CD.STORE_ID= '" + storeId + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    StockNewGetterSetter sb = new StockNewGetterSetter();

                    sb.setSku_cd(dbcursor.getString(dbcursor.getColumnIndexOrThrow("SKU_CD")));

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();

                if (list.size() > 0) {
                    return true;
                } else {
                    return false;
                }

            }

        } catch (Exception e) {
            return false;
        }

        return false;
    }

    //Header with cam data
    public ArrayList<StockNewGetterSetter> getHeaderStockInsertData(JourneyPlan jcp) {
        Log.d("Fetching", "Storedata--------------->Start<------------");
        ArrayList<StockNewGetterSetter> list = new ArrayList<StockNewGetterSetter>();
        Cursor dbcursor = null;

        try {

            dbcursor = db.rawQuery("SELECT DISTINCT CATEGORY_CD, CATEGORY, IMAGE_CAT_ONE FROM STOCK_IMAGE WHERE STORE_ID ='" + jcp.getStoreId() + "'AND VISIT_DATE  ='" + jcp.getVisitDate() + "'", null);


            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    StockNewGetterSetter sb = new StockNewGetterSetter();
                    sb.setCategory_cd(dbcursor.getString(dbcursor.getColumnIndexOrThrow("CATEGORY_CD")));
                    sb.setCategory(dbcursor.getString(dbcursor.getColumnIndexOrThrow("CATEGORY")));
                    sb.setImg_cat_one(dbcursor.getString(dbcursor.getColumnIndexOrThrow("IMAGE_CAT_ONE")));


                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {
            Log.d("Exception", " when fetching Header!!!!!!!!!!! " + e.toString());
            return list;
        }
        Log.d("Fetching ", "Header stock---------------------->Stop<-----------");
        return list;
    }

    public ArrayList<StockNewGetterSetter> getmappingStockDataNew(JourneyPlan jcp) {
        Log.d("Fetching", "Storedata--------------->Start<------------");
        ArrayList<StockNewGetterSetter> list = new ArrayList<StockNewGetterSetter>();
        Cursor dbcursor = null;

        try {

            dbcursor = db.rawQuery("Select distinct ca.Category_Id, ca.Category from Mapping_Stock m " +
                    " inner join Sku_Master sk on m.Sku_Id = sk.Sku_Id " +
                    " inner join Brand_Master br on sk.Brand_Id = br.Brand_Id " +
                    " Inner join Sub_Category_Master sb on br.Sub_category_Id = sb.Sub_Category_Id " +
                    " Inner join Category_Master ca on sb.Category_Id = ca.Category_Id " +
                    " where m.State_Id = '" + jcp.getStateId() + "' and m.Store_Category_Id = '" + jcp.getStoreCategoryId() + "' and m.Store_Type_Id = '" + jcp.getStoreTypeId() + "' and m.Distributor_Id = '" + jcp.getDistributorId() + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    StockNewGetterSetter sb = new StockNewGetterSetter();
                    sb.setCategory_cd(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Category_Id")));
                    sb.setCategory(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Category")));


                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {

            return list;
        }

        return list;
    }

    public ArrayList<StockNewGetterSetter> getOpeningStockDataFromInsertDatabase(JourneyPlan jcp, String categord_cd) {
        Log.d("FetchingOpening", " Stock data--------------->Start<------------");
        ArrayList<StockNewGetterSetter> list = new ArrayList<StockNewGetterSetter>();
        Cursor dbcursor = null;

        try {

            dbcursor = db.rawQuery("SELECT * FROM STOCK_DATA WHERE STORE_ID ='" + jcp.getStoreId() + "' AND CATEGORY_CD=" + categord_cd + "", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    StockNewGetterSetter sb = new StockNewGetterSetter();
                    sb.setSku(dbcursor.getString(dbcursor.getColumnIndexOrThrow("SKU")));
                    sb.setSku_cd(dbcursor.getString(dbcursor.getColumnIndexOrThrow("SKU_CD")));
                    sb.setStock1(dbcursor.getString(dbcursor.getColumnIndexOrThrow("OPENING_STOCK")));
                    sb.setFocus(dbcursor.getString(dbcursor.getColumnIndexOrThrow("FOCUS")));

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {
            Log.d("Exception ", "when fetching opening stock!!!!!!!!!!!" + e.toString());
            return list;
        }

        Log.d("FetchingOPening", " midday---------------------->Stop<-----------");

        return list;
    }

    public ArrayList<StockNewGetterSetter> getStockSkuDataNew(JourneyPlan jcp, String category_id) {
        Log.d("Fetching", "Storedata--------------->Start<------------");
        ArrayList<StockNewGetterSetter> list = new ArrayList<StockNewGetterSetter>();
        Cursor dbcursor = null;

        try {

            dbcursor = db.rawQuery("Select distinct sk.Sku_Id, sk.Sku,m.Focus from Mapping_Stock m " +
                    " inner join Sku_Master sk on m.Sku_Id = sk.Sku_Id " +
                    " inner join Brand_Master br on sk.Brand_Id = br.Brand_Id " +
                    " Inner join Sub_Category_Master sb on br.Sub_category_Id = sb.Sub_Category_Id " +
                    " Inner join Category_Master ca on sb.Category_Id = ca.Category_Id " +
                    " where m.State_Id = '" + jcp.getStateId() + "' and m.Store_Category_Id = '" + jcp.getStoreCategoryId() +
                    "' and m.Store_Type_Id = '" + jcp.getStoreTypeId() + "'  and m.Distributor_Id = '" + jcp.getDistributorId() + "'and ca.Category_Id = '" + category_id + "'", null);


            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    StockNewGetterSetter sb = new StockNewGetterSetter();

                    sb.setSku_cd(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Sku_Id")));
                    sb.setSku(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Sku")));
                    sb.setFocus(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Focus")));
                    sb.setStock1("");

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {
            Log.d("Exception", " when fetching opening stock!!!!!!!!!!! " + e.toString());
            return list;
        }

        Log.d("Fetching", " opening stock---------------------->Stop<-----------");
        return list;
    }

    public ArrayList<StockNewGetterSetter> getMiddayStockInsertDatabase(JourneyPlan jcp, String categord_cd) {
        Log.d("Fetching", "Mid Stock data--------------->Start<------------");
        ArrayList<StockNewGetterSetter> list = new ArrayList<StockNewGetterSetter>();
        Cursor dbcursor = null;

        try {

            dbcursor = db.rawQuery("SELECT * FROM STOCK_DATA WHERE STORE_ID ='" + jcp.getStoreId() + "' AND CATEGORY_CD=" + categord_cd + "", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    StockNewGetterSetter sb = new StockNewGetterSetter();

                    sb.setSku(dbcursor.getString(dbcursor.getColumnIndexOrThrow("SKU")));
                    sb.setSku_cd(dbcursor.getString(dbcursor.getColumnIndexOrThrow("SKU_CD")));
                    sb.setStock1(dbcursor.getString(dbcursor.getColumnIndexOrThrow("OPENING_STOCK")));
                    sb.setEd_midFacing(dbcursor.getString(dbcursor.getColumnIndexOrThrow("MIDDAY_STOCK")));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {

            return list;
        }

        return list;
    }

    //Update Midday Data with Brand
    public void UpdateMiddayStocklistData(JourneyPlan jcp, HashMap<StockNewGetterSetter, List<StockNewGetterSetter>> data,
                                          List<StockNewGetterSetter> save_listDataHeader) {
        ContentValues values1 = new ContentValues();

        try {
            ArrayList<HeaderGetterSetter> list = new ArrayList<HeaderGetterSetter>();
            list = getHeaderStock(jcp.getStoreId());

            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < data.get(save_listDataHeader.get(i)).size(); j++) {
                    values1.put("MIDDAY_STOCK", data.get(save_listDataHeader.get(i)).get(j).getEd_midFacing());
                    db.update(CommonString.TABLE_STOCK_DATA, values1, "Common_Id" + "='" + Integer.parseInt(list.get(i).getKeyId()) + "' AND SKU_CD " + "='" + Integer.parseInt(data.get(save_listDataHeader.get(i)).get(j).getSku_cd()) + "'", null);
                }
            }
        } catch (Exception ex) {
            Log.d("Database ", "Exception while Insert Posm Master Data " + ex.toString());
        }
    }

    //closing stock
    public ArrayList<StockNewGetterSetter> getClosingStockDataFromDatabase(String category_cd, int store_cd) {
        Log.d("Fetching", "Opening Stock data--------------->Start<------------");
        ArrayList<StockNewGetterSetter> list = new ArrayList<StockNewGetterSetter>();
        Cursor dbcursor = null;

        try {

            dbcursor = db.rawQuery("SELECT DISTINCT SD.Sku_Id AS SKU_CD, SD.Sku AS SKU,SD.Brand_Id AS BRAND_CD, S.OPENING_STOCK" +
                    " AS OPENING_STOCK, S.MIDDAY_STOCK AS MIDDAY_STOCK,S.CLOSING_STOCK AS CLOSING_STOCK,S.FOCUS AS FOCUS" +
                    " FROM MAPPING_STOCK CD " +
                    "INNER JOIN SKU_MASTER SD ON CD.Sku_Id = SD.Sku_Id " +
                    "INNER JOIN (SELECT * FROM STOCK_DATA WHERE STORE_ID = " + store_cd + " )  S ON SD.Sku_Id=S.SKU_CD " +
                    "WHERE S.CATEGORY_CD ='" + category_cd + "' ORDER BY SD.Sku_Sequence", null);


            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    StockNewGetterSetter sb = new StockNewGetterSetter();
                    sb.setSku_cd(dbcursor.getString(dbcursor.getColumnIndexOrThrow("SKU_CD")));
                    sb.setSku(dbcursor.getString(dbcursor.getColumnIndexOrThrow("SKU")));
                    sb.setBrand_cd(dbcursor.getString(dbcursor.getColumnIndexOrThrow("BRAND_CD")));
                    sb.setSumofSTOCK(dbcursor.getString(dbcursor.getColumnIndexOrThrow("OPENING_STOCK")));
                    sb.setEd_midFacing(dbcursor.getString(dbcursor.getColumnIndexOrThrow("MIDDAY_STOCK")));
                    sb.setEd_closingFacing(dbcursor.getString(dbcursor.getColumnIndexOrThrow("CLOSING_STOCK")));
                    sb.setFocus(dbcursor.getString(dbcursor.getColumnIndexOrThrow("FOCUS")));

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {
            return list;
        }

        return list;
    }


    //Update Closing Data with Brand

    public long UpdateClosingStocklistData(JourneyPlan jcp,
                                           HashMap<StockNewGetterSetter, List<StockNewGetterSetter>> data,
                                           List<StockNewGetterSetter> save_listDataHeader) {
        long val = 0;
        ContentValues values1 = new ContentValues();

        try {
            ArrayList<HeaderGetterSetter> list = new ArrayList<>();
            list = getHeaderStock(jcp.getStoreId());

            db.beginTransaction();
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < data.get(save_listDataHeader.get(i)).size(); j++) {
                    values1.put("CLOSING_STOCK", data.get(save_listDataHeader.get(i)).get(j).getEd_closingFacing());
                    val =  db.update(CommonString.TABLE_STOCK_DATA, values1, "Common_Id" + "='" + Integer.parseInt(list.get(i).getKeyId()) + "' AND SKU_CD " + "='" + data.get(save_listDataHeader.get(i)).get(j).getSku_cd() + "'", null);
                }
            }
            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (Exception ex) {
            Log.d("Database", " Exception while Insert Posm Master Data " + ex.toString());
        }
        return val;
    }


    //sos insert data
    public ArrayList<ShareOfShelfGetterSetter> getHeaderShareOfSelfImageData(String store_cd) {
        Log.d("Fetching", "Storedata--------------->Start<------------");
        ArrayList<ShareOfShelfGetterSetter> list = new ArrayList<ShareOfShelfGetterSetter>();
        Cursor dbcursor = null;

        try {

            dbcursor = db.rawQuery("SELECT SB.SUB_CATEGORY_CD AS SUB_CATEGORY_CD, CA.CATEGORY||'-'||SB.SUB_CATEGORY AS SUB_CATEGORY, IFNULL(D.IMAGE_CAT_FACING,'') AS IMAGE_CAT_FACING, IFNULL(D.CAT_FACING,'')" +
                    "  AS CAT_FACING,SB.CATEGORY_CD AS CATEGORY_CD FROM CATEGORY_MASTER CA " +
                    " INNER JOIN (SELECT * FROM SUB_CATEGORY_MASTER WHERE SUB_CATEGORY_CD IN( SELECT DISTINCT SUB_CATEGORY_CD FROM BRAND_MASTER WHERE BRAND_CD IN(" +
                    " SELECT BRAND_CD FROM MAPPING_SOS))) AS  SB ON CA.CATEGORY_CD = SB.CATEGORY_CD " +
                    " LEFT JOIN " +
                    "(SELECT * FROM DR_CATEGORY_SHARE_OF_SHELF_IMAGE WHERE STORE_CD='" + store_cd + "')" + " AS D ON SB.SUB_CATEGORY_CD = D.SUB_CATEGORY_CD" +
                    " INNER JOIN MAPPING_USER_CATEGORY MC ON SB.CATEGORY_CD = MC.CATEGORY_CD ", null);


            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    ShareOfShelfGetterSetter sb = new ShareOfShelfGetterSetter();
                    sb.setCategory_cd(dbcursor.getString(dbcursor.getColumnIndexOrThrow("SUB_CATEGORY_CD")));
                    sb.setCategory(dbcursor.getString(dbcursor.getColumnIndexOrThrow("SUB_CATEGORY")));
                    sb.setCat_facing(dbcursor.getString(dbcursor.getColumnIndexOrThrow("CAT_FACING")));
                    sb.setImg_cat_facing(dbcursor.getString(dbcursor.getColumnIndexOrThrow("IMAGE_CAT_FACING")));

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {
            Log.d("Exception", " when fetching Header!!!!!!!!!!! " + e.toString());
            return list;
        }
        Log.d("Fetching ", "Header stock---------------------->Stop<-----------");
        return list;
    }

    //sos defaul data
    public ArrayList<ShareOfShelfGetterSetter> getmappingShareOfShelfData() {
        Log.d("Fetching", "Storedata--------------->Start<------------");
        ArrayList<ShareOfShelfGetterSetter> list = new ArrayList<ShareOfShelfGetterSetter>();
        Cursor dbcursor = null;

        try {

            dbcursor = db.rawQuery("select Distinct ca.Category_Id, ca.Category,sb.Sub_Category_Id,sb.Sub_Category  from mapping_Share_Of_Shelf m inner join Brand_Master br on m.brand_id = br.Brand_Id " +
                    " Inner join sub_category_Master sb on br.Sub_Category_Id = sb.Sub_Category_Id " +
                    " Inner join Category_Master ca on sb.Category_Id = ca.Category_Id ", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    ShareOfShelfGetterSetter sb = new ShareOfShelfGetterSetter();
                    sb.setCategory_cd(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Category_Id")));
                    sb.setCategory(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Category")));
                    sb.setSub_category_cd(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Sub_Category_Id")));
                    sb.setSub_category(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Sub_Category")));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {
            Log.d("Exception ", "when fetching opening stock!!!!!!!!!!!" + e.toString());
            return list;
        }

        Log.d("Fetching ", "opening stock---------------------->Stop<-----------");
        return list;
    }

    //sos sku default data
    public ArrayList<ShareOfShelfGetterSetter> getShareofShelfBrandData(String sub_category_id) {
        ArrayList<ShareOfShelfGetterSetter> list = new ArrayList<ShareOfShelfGetterSetter>();
        Cursor dbcursor = null;

        try {

            dbcursor = db.rawQuery("select Distinct Br.Brand_Id, br.Brand from mapping_Share_Of_Shelf m inner join Brand_Master br on m.brand_id = br.Brand_Id " +
                    " Inner join sub_category_Master sb on br.Sub_category_Id = sb.Sub_category_Id " +
                    " Inner join Category_Master ca on sb.Category_Id = ca.Category_Id " +
                    " Where sb.Sub_category_Id ='" + sub_category_id + "'", null);


            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    ShareOfShelfGetterSetter sb = new ShareOfShelfGetterSetter();
                    sb.setBrand_cd(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Brand_Id")));
                    sb.setBrand(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Brand")));

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {
            return list;
        }

        return list;
    }

    public void InsertCategoryShareofSelflistData(String storeid, String visit_date,
                                                  HashMap<ShareOfShelfGetterSetter, List<ShareOfShelfGetterSetter>> data, List<ShareOfShelfGetterSetter> save_listDataHeader) {

        db.delete(CommonString.TABLE_CATEGORY_SHARE_OF_SHELF_IMAGE, CommonString.KEY_STORE_ID + "='" + storeid + "'", null);
        db.delete(CommonString.TABLE_SHARE_OF_SHELF_FACING_DATA, CommonString.KEY_STORE_ID + "='" + storeid + "'", null);
        ContentValues values = new ContentValues();
        ContentValues values1 = new ContentValues();


        try {
            db.beginTransaction();
            for (int i = 0; i < save_listDataHeader.size(); i++) {
                values.put("STORE_ID", storeid);
                values.put("VISIT_DATE", visit_date);
                values.put("CATEGORY_CD", save_listDataHeader.get(i).getCategory_cd());
                values.put("SUB_CATEGORY_CD", save_listDataHeader.get(i).getSub_category_cd());
                values.put("SUB_CATEGORY", save_listDataHeader.get(i).getSub_category());
                values.put("CATEGORY", save_listDataHeader.get(i).getCategory());
                values.put("CAT_FACING", save_listDataHeader.get(i).getCat_facing());
                values.put("IMAGE_CAT_FACING", save_listDataHeader.get(i).getImg_cat_facing());
                long l = db.insert(CommonString.TABLE_CATEGORY_SHARE_OF_SHELF_IMAGE, null, values);

                for (int j = 0; j < data.get(save_listDataHeader.get(i)).size(); j++) {
                    values1.put("Common_Id", (int) l);
                    values1.put("STORE_ID", storeid);
                    values1.put("CATEGORY_CD", save_listDataHeader.get(i).getCategory_cd());
                    values1.put("CATEGORY", save_listDataHeader.get(i).getCategory());
                    values1.put("SUB_CATEGORY_CD", save_listDataHeader.get(i).getSub_category_cd());
                    values1.put("SUB_CATEGORY", save_listDataHeader.get(i).getSub_category());
                    values1.put("BRAND_CD", data.get(save_listDataHeader.get(i)).get(j).getBrand_cd());
                    values1.put("BRAND", data.get(save_listDataHeader.get(i)).get(j).getBrand());
                    values1.put("FACING", data.get(save_listDataHeader.get(i)).get(j).getFacing());

                    db.insert(CommonString.TABLE_SHARE_OF_SHELF_FACING_DATA, null, values1);
                }
            }
            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (Exception ex) {
            Log.d("Database Exception", " while Insert Posm Master Data " + ex.toString());
        }
    }

    //sampling data
    public long insertSampledData(JourneyPlan jcp, String user_name, ArrayList<SampledGetterSetter> list) {
        db.delete(CommonString.TABLE_INSERT_SAMPLED_DATA, "STORE_ID" + "='" + jcp.getStoreId() + "'AND VISIT_DATE='" + jcp.getVisitDate() + "'", null);
        db.delete(CommonString.TABLE_INSERT_SAMPLED_CHCEKLIST_DATA, "STORE_ID" + "='" + jcp.getStoreId()+"'", null);
        long l = 0,l2=0;

        try {
            for (int i = 0; i < list.size(); i++) {
                ContentValues values = new ContentValues();
                values.put("STORE_ID", jcp.getStoreId());
                values.put("USER_ID", user_name);
                values.put("VISIT_DATE", jcp.getVisitDate());
                values.put("CATEGORY_CD", list.get(i).getCategory_cd());
                values.put("CATEGORY", list.get(i).getCategory());
                values.put("SKU_CD", list.get(i).getSku_cd());
                values.put("SKU", list.get(i).getSku());
                values.put("EMAIL", list.get(i).getEmail());
                values.put("SAMPLED", list.get(i).getSampled());
                values.put("PHOTO", list.get(i).getSampled_img());
                values.put("FEEDBACK", list.get(i).getFeedback());
                values.put("MOBILE", list.get(i).getMobile());
                values.put("NAME", list.get(i).getName());
                values.put("CHECKBOX", list.get(i).isExists());

                l = db.insert(CommonString.TABLE_INSERT_SAMPLED_DATA, null, values);
                int val=0;

                for (int j = 0; j < list.get(i).getSamplingChecklistData().size(); j++) {
                    if(list.get(i).getSamplingChecklistData().get(j).getSamplingChecklistId()==1){
                        val =  list.get(i).getSamplingChecklistData().get(j).getSampling_Correct_Answer_Cd();
                    }

                    if(list.get(i).getSamplingChecklistData().get(j).getSamplingChecklistId()==2){
                        if(val == 1){
                            ContentValues values1 = new ContentValues();
                            values1.put("STORE_ID", jcp.getStoreId());
                            values1.put("COMMON_ID", l);
                            values1.put("CHCEKLIST_ID", list.get(i).getSamplingChecklistData().get(j).getSamplingChecklistId());
                            values1.put("CHCEKLIST_CORRECT_ANSWER_CD", list.get(i).getSamplingChecklistData().get(j).getSampling_Correct_Answer_Cd());
                            String txt="";
                            int count=0;
                            if(list.get(i).getSamplingChecklistData().get(j).getSelectedSamplingData().size() > 0) {
                                for (int k = 0; k < list.get(i).getSamplingChecklistData().get(j).getSelectedSamplingData().size(); k++) {
                                    if (list.get(i).getSamplingChecklistData().get(j).getSelectedSamplingData().get(k).isSelected()) {

                                        if (count == 0) {
                                            txt = list.get(i).getSamplingChecklistData().get(j).getSelectedSamplingData().get(k).getName();
                                        } else {
                                            txt = txt + " , " + list.get(i).getSamplingChecklistData().get(j).getSelectedSamplingData().get(k).getName();
                                        }

                                        count++;
                                    }
                                }
                                values1.put("CHCEKLIST_ANSWER", txt);
                            }else{
                                values1.put("CHCEKLIST_ANSWER", list.get(i).getSamplingChecklistData().get(j).getSampling_Correct_Answer());
                            }
                            l2 = db.insert(CommonString.TABLE_INSERT_SAMPLED_CHCEKLIST_DATA, null, values1);
                        }
                    }else{
                        ContentValues values2 = new ContentValues();
                        values2.put("STORE_ID", jcp.getStoreId());
                        values2.put("COMMON_ID", l);
                        values2.put("CHCEKLIST_ID", list.get(i).getSamplingChecklistData().get(j).getSamplingChecklistId());
                        values2.put("CHCEKLIST_CORRECT_ANSWER_CD", list.get(i).getSamplingChecklistData().get(j).getSampling_Correct_Answer_Cd());
                        values2.put("CHCEKLIST_ANSWER", list.get(i).getSamplingChecklistData().get(j).getSampling_Correct_Answer());
                        l2 = db.insert(CommonString.TABLE_INSERT_SAMPLED_CHCEKLIST_DATA, null, values2);
                    }
                }
            }
        } catch (Exception ex) {

        }
        return l2;
    }

    public ArrayList<SampledGetterSetter> getSamplingData(String store_id) {

        ArrayList<SampledGetterSetter> list = new ArrayList<>();
        Cursor dbcursor = null;
        try {

          //  dbcursor = db.rawQuery("SELECT * FROM SAMPLING_MASTER WHERE SAMPLE_CD IN(SELECT SAMPLE_CD FROM MAPPING_SAMPLING WHERE STORE_CD ='" + store_cd + "' ORDER BY SAMPLE_CD)", null);
            dbcursor = db.rawQuery("select distinct SM.Sku_Id,SM.Sku from Mapping_Sampling  MS inner join Sku_Master SM on MS.Sku_Id = SM.Sku_Id WHERE MS.Store_Id ='" + store_id + "'", null);

          //  select distinct SM.Sku_Id,SM.Sku from Mapping_Sampling  MS inner join Sku_Master SM on MS.Sku_Id = SM.Sku_Id where MS.Store_Id = '6'

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    SampledGetterSetter sb = new SampledGetterSetter();
                    sb.setSAMPLE_CD(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Sku_Id")));
                    sb.setSAMPLE(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Sku")));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {

            return list;
        }

        return list;
    }

    public ArrayList<SampledGetterSetter> getFeedbackMasterData() {

        ArrayList<SampledGetterSetter> list = new ArrayList<>();
        Cursor dbcursor = null;

        try {

            dbcursor = db.rawQuery("select * from Feedback_Master ", null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    SampledGetterSetter df = new SampledGetterSetter();
                    df.setFEEDBACK(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Feedback")));

                    list.add(df);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            return list;
        }
        return list;

    }

    public ArrayList<SampledGetterSetter> getinsertedsampledData(String store_cd, String visit_date) {
        Log.d("Fetching", "Storedata--------------->Start<------------");

        ArrayList<SampledGetterSetter> list = new ArrayList<>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("Select * from SAMPLED_DATA where STORE_ID='" + store_cd + "'AND VISIT_DATE='" + visit_date + "'", null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    SampledGetterSetter sb = new SampledGetterSetter();

                    sb.setSku_cd(dbcursor.getString(dbcursor.getColumnIndexOrThrow("SKU_CD")));
                    sb.setSku(dbcursor.getString(dbcursor.getColumnIndexOrThrow("SKU")));
                    sb.setCategory_cd(dbcursor.getString(dbcursor.getColumnIndexOrThrow("CATEGORY_CD")));
                    sb.setCategory(dbcursor.getString(dbcursor.getColumnIndexOrThrow("CATEGORY")));
                    sb.setSampled(dbcursor.getString(dbcursor.getColumnIndexOrThrow("SAMPLED")));
                    sb.setSampled_img(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PHOTO")));
                    sb.setFeedback(dbcursor.getString(dbcursor.getColumnIndexOrThrow("FEEDBACK")));
                    sb.setMobile(dbcursor.getString(dbcursor.getColumnIndexOrThrow("MOBILE")));
                    sb.setEmail(dbcursor.getString(dbcursor.getColumnIndexOrThrow("EMAIL")));
                    sb.setName(dbcursor.getString(dbcursor.getColumnIndexOrThrow("NAME")));
                    sb.setKey_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow("KEY_ID")));
                    String value = dbcursor.getString(dbcursor.getColumnIndexOrThrow("CHECKBOX"));
                    if (value.equals("0")) {
                        sb.setExists(false);
                    } else {
                        sb.setExists(true);
                    }
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {
            return list;
        }
        return list;
    }

    public void removesampledata(String user_id) {
        db.execSQL("DELETE FROM SAMPLED_DATA WHERE KEY_ID = '" + user_id + "'");
    }



    //check if table is empty
    public boolean isOpeningDataFilled(String storeId) {
        boolean filled = false;
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT  OPENING_STOCK " + "FROM STOCK_DATA WHERE STORE_ID= '" + storeId + "'", null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                int icount = dbcursor.getCount();
                dbcursor.close();
                if (icount > 0) {
                    filled = true;
                } else {
                    filled = false;
                }
            }
        } catch (Exception e) {

            return filled;
        }
        return filled;
    }

    public boolean isClosingDataFilled1(int store_id) {
        boolean filled = false;
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT CLOSING_STOCK FROM STOCK_DATA " + "WHERE STORE_ID= '" + store_id + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    if (dbcursor.getString(dbcursor.getColumnIndexOrThrow("CLOSING_STOCK")) == null || dbcursor.getString(dbcursor.getColumnIndexOrThrow("CLOSING_STOCK")).equals("")) {
                        filled = false;
                        break;
                    } else {
                        filled = true;
                    }
                    dbcursor.moveToNext();
                }
                dbcursor.close();
            }

        } catch (Exception e) {
            return filled;
        }
        return filled;
    }

    public boolean isClosingDataFilled(int store_id) {
        boolean filled = false;
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT CLOSING_STOCK FROM STOCK_DATA " + "WHERE STORE_ID= '" + store_id + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    if (dbcursor.getString(dbcursor.getColumnIndexOrThrow("CLOSING_STOCK")) == null || dbcursor.getString(dbcursor.getColumnIndexOrThrow("CLOSING_STOCK")).equals("")) {
                        filled = false;
                        break;
                    } else {
                        filled = true;
                    }
                    dbcursor.moveToNext();
                }
                dbcursor.close();
            }

        } catch (Exception e) {
            return filled;
        }

        return filled;
    }


    public ArrayList<ShareOfShelfGetterSetter> getSosFromInsertDatabase(JourneyPlan jcp, String sub_category_cd) {
        Log.d("FetchingOpening", " Stock data--------------->Start<------------");
        ArrayList<ShareOfShelfGetterSetter> list = new ArrayList<ShareOfShelfGetterSetter>();
        Cursor dbcursor = null;

        try {

            dbcursor = db.rawQuery("SELECT * FROM DR_SHARE_OF_SHELF_FACING_DATA WHERE STORE_ID ='" + jcp.getStoreId() + "' AND SUB_CATEGORY_CD=" + sub_category_cd + "", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    ShareOfShelfGetterSetter sb = new ShareOfShelfGetterSetter();
                    sb.setBrand(dbcursor.getString(dbcursor.getColumnIndexOrThrow("BRAND")));
                    sb.setBrand_cd(dbcursor.getString(dbcursor.getColumnIndexOrThrow("BRAND_CD")));
                    sb.setFacing(dbcursor.getString(dbcursor.getColumnIndexOrThrow("FACING")));

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {
            Log.d("Exception ", "when fetching opening stock!!!!!!!!!!!" + e.toString());
            return list;
        }

        Log.d("FetchingOPening", " midday---------------------->Stop<-----------");

        return list;
    }

    public ArrayList<ShareOfShelfGetterSetter> getHeaderSosInsertData(JourneyPlan jcp) {
        Log.d("Fetching", "Storedata--------------->Start<------------");
        ArrayList<ShareOfShelfGetterSetter> list = new ArrayList<ShareOfShelfGetterSetter>();
        Cursor dbcursor = null;

        try {

            dbcursor = db.rawQuery("SELECT DISTINCT CATEGORY_CD, CATEGORY, IMAGE_CAT_FACING,SUB_CATEGORY_CD,SUB_CATEGORY FROM DR_CATEGORY_SHARE_OF_SHELF_IMAGE WHERE STORE_ID ='" + jcp.getStoreId() + "'AND VISIT_DATE  ='" + jcp.getVisitDate() + "'", null);


            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    ShareOfShelfGetterSetter sb = new ShareOfShelfGetterSetter();
                    sb.setSub_category_cd(dbcursor.getString(dbcursor.getColumnIndexOrThrow("SUB_CATEGORY_CD")));
                    sb.setSub_category(dbcursor.getString(dbcursor.getColumnIndexOrThrow("SUB_CATEGORY")));
                    sb.setCategory_cd(dbcursor.getString(dbcursor.getColumnIndexOrThrow("CATEGORY_CD")));
                    sb.setCategory(dbcursor.getString(dbcursor.getColumnIndexOrThrow("CATEGORY")));
                    sb.setImg_cat_facing(dbcursor.getString(dbcursor.getColumnIndexOrThrow("IMAGE_CAT_FACING")));

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {
            Log.d("Exception", " when fetching Header!!!!!!!!!!! " + e.toString());
            return list;
        }
        Log.d("Fetching ", "Header stock---------------------->Stop<-----------");
        return list;
    }


//additional visibility
public long insertmarketintelligenceData(JourneyPlan jcp, String user_name,ArrayList<MarketIntelligenceGetterSetter> list) {
    db.delete(CommonString.TABLE_INSERT_MARKET_INTELLI_DATA, "STORE_ID" + "='" + jcp.getStoreId() + "'AND VISIT_DATE='" + jcp.getVisitDate() + "'", null);
    long l = 0;
    ContentValues values = new ContentValues();
    try {

        for (int i = 0; i < list.size(); i++) {
            values.put("STORE_ID", jcp.getStoreId());
            values.put("USER_ID", user_name);
            values.put("VISIT_DATE", jcp.getVisitDate());
            values.put("CHECKBOX", list.get(i).isExists());
            values.put("COMPANY_CD", list.get(i).getCompany_cd());
            values.put("COMPANY", list.get(i).getCompany());
            values.put("CATEGORY_CD", list.get(i).getCategory_cd());
            values.put("CATEGORY", list.get(i).getCategory());
            values.put("PROMOTYPE_CD", list.get(i).getPromotype_cd());
            values.put("PROMOTYPE", list.get(i).getPromotype());
            values.put("PHOTO", list.get(i).getPhoto());
            values.put("REMARK", list.get(i).getRemark());

            l = db.insert(CommonString.TABLE_INSERT_MARKET_INTELLI_DATA, null, values);
        }
    } catch (Exception ex) {

    }
    return l;
}

    public ArrayList<MarketIntelligenceGetterSetter> getinsertedMarketIntelligenceData(JourneyPlan jcp) {

        ArrayList<MarketIntelligenceGetterSetter> list = new ArrayList<>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("Select * from MARKET_INTELLIGENCE where STORE_ID='" + jcp.getStoreId() + "'AND VISIT_DATE='" + jcp.getVisitDate() + "'", null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    MarketIntelligenceGetterSetter sb = new MarketIntelligenceGetterSetter();
                    String value = dbcursor.getString(dbcursor.getColumnIndexOrThrow("CHECKBOX"));
                    if (value.equals("0")) {
                        sb.setExists(false);
                    } else {
                        sb.setExists(true);
                    }
                    sb.setCompany_cd(dbcursor.getString(dbcursor.getColumnIndexOrThrow("COMPANY_CD")));
                    sb.setCompany(dbcursor.getString(dbcursor.getColumnIndexOrThrow("COMPANY")));
                    sb.setCategory_cd(dbcursor.getString(dbcursor.getColumnIndexOrThrow("CATEGORY_CD")));
                    sb.setCategory(dbcursor.getString(dbcursor.getColumnIndexOrThrow("CATEGORY")));
                    sb.setPhoto(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PHOTO")));
                    sb.setRemark(dbcursor.getString(dbcursor.getColumnIndexOrThrow("REMARK")));
                    sb.setKey_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow("KEY_ID")));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {
            Log.d("Exception", " when fetching opening stock!!!!!!!!!!! " + e.toString());
            return list;
        }
        Log.d("Fetching", " opening stock---------------------->Stop<-----------");
        return list;
    }

    public void removeallmarketIData(JourneyPlan jcp) {
        db.delete(CommonString.TABLE_INSERT_MARKET_INTELLI_DATA, CommonString.KEY_STORE_ID + "='" + jcp.getStoreId() + "'", null);
    }

    public void remove(String user_id) {
        db.execSQL("DELETE FROM MARKET_INTELLIGENCE WHERE KEY_ID = '" + user_id + "'");
    }

    public ArrayList<DisplayMaster> getassetData() {
        ArrayList<DisplayMaster> list = new ArrayList<>();
        Cursor dbcursor = null;
        try {

            dbcursor = db.rawQuery("SELECT * FROM Display_Master ", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    DisplayMaster sb = new DisplayMaster();
                    sb.setDisplayId(dbcursor.getInt(dbcursor.getColumnIndexOrThrow("Display_Id")));
                    sb.setDisplay(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Display")));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {

            return list;
        }

        return list;
    }

    public ArrayList<SubCategoryMaster> getsubcategoryTypeData(String category_id) {

        ArrayList<SubCategoryMaster> list = new ArrayList<>();
        Cursor dbcursor = null;
        try {

            dbcursor = db.rawQuery("Select * from Sub_Category_Master " + "where Category_Id='" + category_id + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    SubCategoryMaster sb = new SubCategoryMaster();
                    sb.setSubCategoryId(dbcursor.getInt(dbcursor.getColumnIndexOrThrow("Sub_Category_Id")));
                    sb.setSubCategory(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Sub_Category")));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {

            return list;
        }

        return list;
    }


    public ArrayList<BrandMaster> getbrandData(String categorysub_cd) {
        ArrayList<BrandMaster> list = new ArrayList<>();
        Cursor dbcursor = null;
        try {

            dbcursor = db.rawQuery("Select * from Brand_Master " + "where Sub_Category_Id='" + categorysub_cd + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    BrandMaster sb = new BrandMaster();
                    sb.setBrandId(dbcursor.getInt(dbcursor.getColumnIndexOrThrow("Brand_Id")));
                    sb.setBrand(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Brand")));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            return list;
        }

        return list;
    }

    public ArrayList<CategoryMaster> getcategorymasterData() {
        ArrayList<CategoryMaster> list = new ArrayList<>();
        Cursor dbcursor = null;
        try {

            dbcursor = db.rawQuery("SELECT * FROM Category_Master  ", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    CategoryMaster sb = new CategoryMaster();
                    sb.setCategoryId(dbcursor.getInt(dbcursor.getColumnIndexOrThrow("Category_Id")));
                    sb.setCategory(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Category")));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            return list;
        }

        return list;
    }
    public boolean isSosFieldData(int store_id) {
        boolean filled = false;
        Cursor dbcursor = null;
        try {

            dbcursor = db.rawQuery("SELECT  STORE_ID " + "FROM DR_SHARE_OF_SHELF_FACING_DATA WHERE STORE_ID= '" + store_id + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    if (dbcursor.getString(dbcursor.getColumnIndexOrThrow("STORE_ID")) == null || dbcursor.getString(dbcursor.getColumnIndexOrThrow("STORE_ID")).equals("")) {
                        filled = false;
                        break;
                    } else {
                        filled = true;
                    }
                    dbcursor.moveToNext();
                }
                dbcursor.close();
            }

        } catch (Exception e) {
            return filled;
        }

        return filled;
    }



    public boolean isMiddayDataFilled1(int store_id) {
        boolean filled = false;
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT MIDDAY_STOCK FROM STOCK_DATA " + "WHERE STORE_ID= '" + store_id + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    if (dbcursor.getString(dbcursor.getColumnIndexOrThrow("MIDDAY_STOCK")) == null || dbcursor.getString(dbcursor.getColumnIndexOrThrow("MIDDAY_STOCK")).equals("")) {
                        filled = false;
                        break;
                    } else {
                        filled = true;
                    }
                    dbcursor.moveToNext();
                }
                dbcursor.close();
            }

        } catch (Exception e) {
            return filled;
        }

        return filled;
    }


    public ArrayList<StockNewGetterSetter> getOpeningStockUpload(String storeId) {
        Log.d("Fetching", "Stock upload data--------------->Start<------------");
        ArrayList<StockNewGetterSetter> list = new ArrayList<StockNewGetterSetter>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT * FROM " + CommonString.TABLE_STOCK_DATA + " WHERE STORE_ID= '" + storeId + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    StockNewGetterSetter sb = new StockNewGetterSetter();
                    sb.setStore_cd(dbcursor.getString(dbcursor.getColumnIndexOrThrow("STORE_ID")));
                    sb.setCategory_cd(dbcursor.getString(dbcursor.getColumnIndexOrThrow("CATEGORY_CD")));
                    sb.setSku_cd(dbcursor.getString(dbcursor.getColumnIndexOrThrow("SKU_CD")));
                    sb.setStock1(dbcursor.getString(dbcursor.getColumnIndexOrThrow("OPENING_STOCK")));
                    sb.setFocus(dbcursor.getString(dbcursor.getColumnIndexOrThrow("FOCUS")));
                    String midday_stock = dbcursor.getString(dbcursor.getColumnIndexOrThrow("MIDDAY_STOCK"));
                    if (midday_stock == null || midday_stock.equals("")) {
                        sb.setEd_midFacing("0");
                    } else {
                        sb.setEd_midFacing(midday_stock);
                    }
                    //Closing stock xml data
                    String closing_stock = dbcursor.getString(dbcursor.getColumnIndexOrThrow("CLOSING_STOCK"));
                    if (closing_stock == null || closing_stock.equals("")) {
                        sb.setEd_closingFacing("0");
                    } else {
                        sb.setEd_closingFacing(closing_stock);
                    }
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {
            return list;
        }

        return list;
    }

    public ArrayList<StockNewGetterSetter> getStockImageUploadData(String storeId) {
        ArrayList<StockNewGetterSetter> list = new ArrayList<StockNewGetterSetter>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT DISTINCT CATEGORY_CD, CATEGORY, IMAGE_CAT_ONE " +
                    "FROM STOCK_IMAGE " +
                    "WHERE STORE_ID ='" + storeId + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    StockNewGetterSetter sb = new StockNewGetterSetter();

                    sb.setCategory_cd(dbcursor.getString(dbcursor.getColumnIndexOrThrow("CATEGORY_CD")));
                    sb.setCategory(dbcursor.getString(dbcursor.getColumnIndexOrThrow("CATEGORY")));
                    sb.setImg_cat_one(dbcursor.getString(dbcursor.getColumnIndexOrThrow("IMAGE_CAT_ONE")));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {
            return list;
        }
        return list;
    }

    public ArrayList<ShareOfShelfGetterSetter> getHeaderShareOfSelfImgUploadData1(String storeId,String visit_date) {
        Log.d("Fetching", "Storedata--------------->Start<------------");
        ArrayList<ShareOfShelfGetterSetter> list = new ArrayList<ShareOfShelfGetterSetter>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT  * from "
                    + CommonString.TABLE_CATEGORY_SHARE_OF_SHELF_IMAGE + " where "
                    + CommonString.KEY_STORE_ID + " = '" + storeId + "' and " + CommonString.KEY_VISIT_DATE + " =  '" + visit_date + "' ", null);


            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    ShareOfShelfGetterSetter sb = new ShareOfShelfGetterSetter();

                    sb.setCategory_cd(dbcursor.getString(dbcursor.getColumnIndexOrThrow("CATEGORY_CD")));
                    sb.setSub_category_cd(dbcursor.getString(dbcursor.getColumnIndexOrThrow("SUB_CATEGORY_CD")));
                    sb.setCategory(dbcursor.getString(dbcursor.getColumnIndexOrThrow("CATEGORY")));
                    sb.setImg_cat_facing(dbcursor.getString(dbcursor.getColumnIndexOrThrow("IMAGE_CAT_FACING")));
                    sb.setKey_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Key_Id")));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {
            return list;
        }
        return list;
    }

    public ArrayList<ShareOfShelfGetterSetter> getShareofShelfBrandDataUpload(String common_id, String storeId) {
        Log.d("Fetching", "Storedata--------------->Start<------------");
        ArrayList<ShareOfShelfGetterSetter> list = new ArrayList<ShareOfShelfGetterSetter>();
        Cursor dbcursor = null;

        try {
            dbcursor = db.rawQuery("SELECT  * from "
                    + CommonString.TABLE_SHARE_OF_SHELF_FACING_DATA + " where "
                    + CommonString.KEY_STORE_ID + " = '" + storeId + "' and " + CommonString.KEY_COMMON_ID + " =  '" + common_id + "' ", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    ShareOfShelfGetterSetter sb = new ShareOfShelfGetterSetter();
                    sb.setBrand_cd(dbcursor.getString(dbcursor.getColumnIndexOrThrow("BRAND_CD")));
                    sb.setBrand(dbcursor.getString(dbcursor.getColumnIndexOrThrow("BRAND")));
                    sb.setFacing(dbcursor.getString(dbcursor.getColumnIndexOrThrow("FACING")));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {
            return list;
        }

        return list;
    }

    public ArrayList<MarketIntelligenceGetterSetter> getPaidVisibilityUploadData(String store_cd, String visit_date) {

        ArrayList<MarketIntelligenceGetterSetter> list = new ArrayList<>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("Select * from MARKET_INTELLIGENCE where STORE_ID='" + store_cd + "'AND VISIT_DATE='" + visit_date + "'", null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    MarketIntelligenceGetterSetter sb = new MarketIntelligenceGetterSetter();
                    String value = dbcursor.getString(dbcursor.getColumnIndexOrThrow("CHECKBOX"));
                    if (value.equals("0")) {
                        sb.setExists(false);
                    } else {
                        sb.setExists(true);
                    }
                    sb.setCompany_cd(dbcursor.getString(dbcursor.getColumnIndexOrThrow("COMPANY_CD")));
                    sb.setCompany(dbcursor.getString(dbcursor.getColumnIndexOrThrow("COMPANY")));
                    sb.setCategory_cd(dbcursor.getString(dbcursor.getColumnIndexOrThrow("CATEGORY_CD")));
                    sb.setCategory(dbcursor.getString(dbcursor.getColumnIndexOrThrow("CATEGORY")));
                    sb.setPhoto(dbcursor.getString(dbcursor.getColumnIndexOrThrow("PHOTO")));
                    sb.setRemark(dbcursor.getString(dbcursor.getColumnIndexOrThrow("REMARK")));
                    sb.setKey_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow("KEY_ID")));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {

            return list;
        }

        return list;
    }


    public long insertGroomingImgData(GroomingGetterSetter groomingImg) {
        db.delete(CommonString.TABLE_GROOMING_IMAGE_DATA, " STORE_ID" + "='" + groomingImg.getStoreCd() + "' AND VISIT_DATE='" + groomingImg.getStore_visit_date() + "'", null);
        ContentValues values = new ContentValues();
        long l = 0;
        try {
            db.beginTransaction();

            values.put(CommonString.KEY_STORE_ID, groomingImg.getStoreCd());
            values.put(CommonString.KEY_VISIT_DATE, groomingImg.getStore_visit_date());
            values.put(CommonString.KEY_IMAGE, groomingImg.getGrooming_image());

            l = db.insert(CommonString.TABLE_GROOMING_IMAGE_DATA, null, values);

            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (Exception ex) {

        }
        return l;
    }

    public boolean isgROOMINGFilled(JourneyPlan jcp) {
        boolean filled = false;
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("SELECT IMAGE FROM STORE_GROOMING_IMAGE_DATA WHERE STORE_ID= '" + jcp.getStoreId() + "'", null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    if (dbcursor.getString(dbcursor.getColumnIndexOrThrow("IMAGE")).equals("")) {
                        filled = false;
                        break;
                    } else {
                        filled = true;
                    }
                    dbcursor.moveToNext();
                }
                dbcursor.close();
            }

        } catch (Exception e) {

            return filled;
        }
        return filled;
    }
    public GroomingGetterSetter getGroomigImgData(String store_cd, String visit_date) {
        GroomingGetterSetter sb = new GroomingGetterSetter();
        Cursor dbcursor = null;
        try {
               dbcursor = db.rawQuery("SELECT * FROM STORE_GROOMING_IMAGE_DATA WHERE STORE_ID ='" + store_cd + "' AND VISIT_DATE='" + visit_date + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {

                    sb.setStoreCd(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
                    sb.setStore_visit_date(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_VISIT_DATE)));
                    sb.setGrooming_image(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_IMAGE)));

                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return sb;
            }

        } catch (Exception e) {

            return sb;
        }

        return sb;
    }


    public boolean isAdditionalVisibilityFilled(int storeId) {
        boolean filled = false;
        Cursor dbcursor = null;
        try {

            dbcursor = db.rawQuery("SELECT  CHECKBOX " + "FROM MARKET_INTELLIGENCE WHERE STORE_ID= '" + storeId + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    if (dbcursor.getString(dbcursor.getColumnIndexOrThrow("CHECKBOX")) == null || dbcursor.getString(dbcursor.getColumnIndexOrThrow("CHECKBOX")).equals("")) {
                        filled = false;
                        break;
                    } else {
                        filled = true;
                    }
                    dbcursor.moveToNext();
                }
                dbcursor.close();
            }

        } catch (Exception e) {

            return filled;
        }

        return filled;
    }


    /** neeraj code **/
    public boolean insertMappingPromotionData(MappingPromotionGetterSetter mappingPromotionGetterSetter) {
        db.delete("Mapping_Promotion", null, null);
        ContentValues values = new ContentValues();
        List<MappingPromotion> data = mappingPromotionGetterSetter.getMappingPromotion();
        try {
            if (data.size() == 0) {
                return false;
            }

            for (int i = 0; i < data.size(); i++) {
                values.put("Id", data.get(i).getId());
                values.put("State_Id", data.get(i).getStateId());
                values.put("Distributor_Id", data.get(i).getDistributorId());
                values.put("Store_Category_Id", data.get(i).getStoreCategoryId());
                values.put("Store_Type_Id", data.get(i).getStoreTypeId());
                values.put("Category_Id", data.get(i).getCategoryId());
                values.put("Promotion", data.get(i).getPromotion());

                long id = db.insert("Mapping_Promotion", null, values);
                if (id == -1) {
                    throw new Exception();
                }
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.d("Database Exception  ", ex.toString());
            return false;
        }
    }
    public boolean insertFeedbackData(FeedbackMasterGetterSetter feedbackMasterGetterSetter) {
        db.delete("Feedback_Master", null, null);
        ContentValues values = new ContentValues();
        List<FeedbackMaster> data = feedbackMasterGetterSetter.getFeedbackMaster();
        try {
            if (data.size() == 0) {
                return false;
            }

            for (int i = 0; i < data.size(); i++) {

                values.put("Feedback", data.get(i).getFeedback());

                long id = db.insert("Feedback_Master", null, values);
                if (id == -1) {
                    throw new Exception();
                }
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.d("Database Exception  ", ex.toString());
            return false;
        }
    }


    public ArrayList<CategoryMaster> getCategoryMasterData(JourneyPlan journeyPlan) {
        ArrayList<CategoryMaster> list = new ArrayList<>();
        Cursor dbcursor = null;
        try {

            dbcursor = db.rawQuery(" select distinct CM.Category,CM.Category_Id from Mapping_Promotion MP " +
                    "inner join Category_Master CM on MP.Category_Id = CM.Category_Id " +
                    "where MP.Store_Category_Id = '" + journeyPlan.getStoreCategoryId() + "' " +
                    "and MP.Store_Type_Id='" + journeyPlan.getStoreTypeId() + "' " +
                    "and MP.Distributor_Id='" + journeyPlan.getDistributorId() + "' " +
                    "and MP.State_Id = '" + journeyPlan.getStateId() + "' ", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    CategoryMaster sb1 = new CategoryMaster();
                    sb1.setCategory(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Category")));
                    sb1.setCategoryId(dbcursor.getInt(dbcursor.getColumnIndexOrThrow("Category_Id")));
                    list.add(sb1);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching", e.toString());
            return list;
        }

        Log.d("Fetching non working", "-------------------");
        return list;
    }

    public ArrayList<CategoryMaster> getPromotionData(Integer categoryId, JourneyPlan journeyPlan) {
        ArrayList<CategoryMaster> list = new ArrayList<>();
        Cursor dbcursor = null;
        try {

            dbcursor = db.rawQuery("select distinct * from Mapping_Promotion " +
                    "where Category_Id = '" + categoryId + "' " +
                    "and Store_Type_Id='" + journeyPlan.getStoreTypeId() + "' " +
                    "and Store_Category_Id='" + journeyPlan.getStoreCategoryId() + "' " +
                    "and Distributor_Id='" + journeyPlan.getDistributorId() + "' " +
                    "and State_Id = '" + journeyPlan.getStateId() + "' ", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    CategoryMaster sb1 = new CategoryMaster();
                    sb1.setPromotion(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Promotion")));
                    sb1.setId(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Id")));
                    list.add(sb1);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching", e.toString());
            return list;
        }

        Log.d("Fetching non working", "-------------------");
        return list;
    }


    public ArrayList<NonExecutionReason> getNonExecutionReason(String menu_id) {
        Log.d("Fetching Data", "------------------");
        Cursor dbcursor = null;
        ArrayList<NonExecutionReason> list = new ArrayList<>();
        NonExecutionReason reason1 = new NonExecutionReason();
        try {
            reason1.setReasonId(0);
            reason1.setReason("Select Reason");
            list.add(reason1);

            dbcursor = db.rawQuery("SELECT  * from Non_Execution_Reason where Menu_Id = '"+menu_id+"'", null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    NonExecutionReason reason = new NonExecutionReason();
                    reason.setReason(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Reason")));
                    reason.setReasonId(Integer.valueOf(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Reason_Id"))));

                    list.add(reason);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception ", e.getMessage());
            return list;
        }
        return list;
    }

    public long insertPromotionCompleteData(HashMap<CategoryMaster, List<CategoryMaster>> listDataChild, List<CategoryMaster> listDataHeader, String store_id, String menu_id, String visit_date, String username) {
        long l = 0, common_id = 0, l2 = 0;
        db.delete(CommonString.TABLE_PROMOTION_HEADER_DATA, "STORE_ID ='" + store_id + "'", null);
        db.delete(CommonString.TABLE_PROMOTION_CHILD_DATA, "STORE_ID ='" + store_id + "'", null);
        ContentValues values;
        ContentValues values1, values2;
        try {

            for (int i = 0; i < listDataHeader.size(); i++) {
                values = new ContentValues();
                values.put(CommonString.KEY_VISIT_DATE, visit_date);
                values.put(CommonString.KEY_USER_ID, username);
                values.put(CommonString.KEY_STORE_ID, store_id);
                values.put(CommonString.KEY_MENU_ID, menu_id);
                values.put(CommonString.KEY_CATEGORY_ID, listDataHeader.get(i).getCategoryId());
                values.put(CommonString.KEY_CATEGORY, listDataHeader.get(i).getCategory());

                common_id = db.insert(CommonString.TABLE_PROMOTION_HEADER_DATA, null, values);
                if (common_id > 0) {

                    for (int j = 0; j < listDataChild.get(listDataHeader.get(i)).size(); j++) {
                        values1 = new ContentValues();
                        values1.put(CommonString.KEY_COMMON_ID, common_id);
                        values1.put(CommonString.KEY_PROMOTION_ID,  listDataChild.get(listDataHeader.get(i)).get(j).getId());
                        values1.put(CommonString.KEY_VISIT_DATE, visit_date);
                        values1.put(CommonString.KEY_USER_ID, username);
                        values1.put(CommonString.KEY_STORE_ID, store_id);
                        values1.put(CommonString.KEY_PROMOTION_PRESENT, listDataChild.get(listDataHeader.get(i)).get(j).getPresent());
                        values1.put(CommonString.KEY_MENU_ID, menu_id);
                        values1.put(CommonString.KEY_CATEGORY_ID,listDataHeader.get(i).getCategoryId());
                        values1.put(CommonString.KEY_REASON_ID, listDataChild.get(listDataHeader.get(i)).get(j).getReasonId());
                        values1.put(CommonString.KEY_IMAGE1, listDataChild.get(listDataHeader.get(i)).get(j).getImage1());
                        values1.put(CommonString.KEY_IMAGE2, listDataChild.get(listDataHeader.get(i)).get(j).getImage2());

                        l = db.insert(CommonString.TABLE_PROMOTION_CHILD_DATA, null, values1);
                    }
                }
            }

            if (l > 0) {
                return l;
            } else {
                return 0;
            }
        } catch (Exception ex) {

            return 0;
        }
    }




    public boolean isPromotionFilledData(Integer storeId) {
        boolean filled = false;
        Cursor dbcursor = null;
        try {

            dbcursor = db.rawQuery("SELECT  STORE_ID " + "FROM " + CommonString.TABLE_PROMOTION_HEADER_DATA + " WHERE STORE_ID = '" + storeId + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    if (dbcursor.getString(dbcursor.getColumnIndexOrThrow("STORE_ID")) == null || dbcursor.getString(dbcursor.getColumnIndexOrThrow("STORE_ID")).equals("")) {
                        filled = false;
                        break;
                    } else {
                        filled = true;
                    }
                    dbcursor.moveToNext();
                }
                dbcursor.close();
            }

        } catch (Exception e) {

            return filled;
        }

        return filled;
    }



    public List<CategoryMaster> getSavedPromotionHeaderData(String store_id, String visit_date) {
        ArrayList<CategoryMaster> list = new ArrayList<>();
        Cursor dbcursor = null;
        try {

            dbcursor = db.rawQuery("select * from " + CommonString.TABLE_PROMOTION_HEADER_DATA + " where " + CommonString.KEY_STORE_ID + " = '" + store_id + "' and " + CommonString.KEY_VISIT_DATE + " = '" + visit_date + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    CategoryMaster sb1 = new CategoryMaster();
                    sb1.setCategory(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_CATEGORY)));
                    sb1.setCategoryId(dbcursor.getInt(dbcursor.getColumnIndexOrThrow(CommonString.KEY_CATEGORY_ID)));
                    sb1.setStore_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
                    sb1.setMenu_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_MENU_ID)));
                    list.add(sb1);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching", e.toString());
            return list;
        }

        Log.d("Fetching non working", "-------------------");
        return list;
    }


    public ArrayList<CategoryMaster> getSavedPromotionInsertedChildData(Integer categoryId, String store_id, String visit_date) {
        ArrayList<CategoryMaster> list = new ArrayList<>();
        Cursor dbcursor = null, dbcursor2 = null;
        String promotion_id = "";
        try {

            dbcursor = db.rawQuery("select * from " + CommonString.TABLE_PROMOTION_CHILD_DATA + " where " + CommonString.KEY_STORE_ID + " = '" + store_id + "' and " + CommonString.KEY_VISIT_DATE + " = '" + visit_date + "' and " + CommonString.KEY_CATEGORY_ID + " = '" + categoryId + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    CategoryMaster sb1 = new CategoryMaster();
                    promotion_id = dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_PROMOTION_ID));
                    sb1.setId(promotion_id);
                    sb1.setPresent(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_PROMOTION_PRESENT)));
                    sb1.setImage1(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_IMAGE1)));
                    sb1.setCategoryId(dbcursor.getInt(dbcursor.getColumnIndexOrThrow(CommonString.KEY_CATEGORY_ID)));
                    sb1.setImage2(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_IMAGE2)));
                    sb1.setReasonId(dbcursor.getInt(dbcursor.getColumnIndexOrThrow(CommonString.KEY_REASON_ID)));
                    list.add(sb1);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching", e.toString());
            return list;
        }

        Log.d("Fetching non working", "-------------------");
        return list;
    }


    public ArrayList<BrandMaster> getBrandMasterData(JourneyPlan journeyPlan) {
        ArrayList<BrandMaster> list = new ArrayList<>();
        Cursor dbcursor = null;
        try {

            dbcursor = db.rawQuery(" select distinct BM.Brand_Id,BM.Brand from Mapping_Paid_Visibility MPV " +
                    "inner join Brand_Master  BM ON MPV.Brand_Id = BM.Brand_Id " +
                    "where MPV.Store_Id = '" + journeyPlan.getStoreId() + "' ", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    BrandMaster sb1 = new BrandMaster();
                    sb1.setBrand(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Brand")));
                    sb1.setBrandId(dbcursor.getInt(dbcursor.getColumnIndexOrThrow("Brand_Id")));
                    list.add(sb1);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching", e.toString());
            return list;
        }

        Log.d("Fetching non working", "-------------------");
        return list;
    }


    public long insertPaidVisibilityCompleteData(HashMap<BrandMaster, List<BrandMaster>> listDataChild, List<BrandMaster> listDataHeader, String store_id, String menu_id, String visit_date, String username) {
        long l = 0, common_id = 0, l2 = 0;
        db.delete(CommonString.TABLE_PAID_VISIBILITY_HEADER_DATA, "STORE_ID ='" + store_id + "'", null);
        db.delete(CommonString.TABLE_PAID_VISIBILITY_CHILD_DATA, "STORE_ID ='" + store_id + "'", null);
        ContentValues values;
        ContentValues values1, values2;
        try {

            for (int i = 0; i < listDataHeader.size(); i++) {
                values = new ContentValues();
                values.put(CommonString.KEY_VISIT_DATE, visit_date);
                values.put(CommonString.KEY_USER_ID, username);
                values.put(CommonString.KEY_STORE_ID, store_id);
                values.put(CommonString.KEY_BRAND_ID, listDataHeader.get(i).getBrandId());
                values.put(CommonString.KEY_BRAND, listDataHeader.get(i).getBrand());

                common_id = db.insert(CommonString.TABLE_PAID_VISIBILITY_HEADER_DATA, null, values);
                if (common_id > 0) {

                    for (int j = 0; j < listDataChild.get(listDataHeader.get(i)).size(); j++) {
                        values1 = new ContentValues();
                        values1.put(CommonString.KEY_COMMON_ID, common_id);
                        values1.put(CommonString.KEY_DISPLAY_ID,  listDataChild.get(listDataHeader.get(i)).get(j).getDisplay_Id());
                        values1.put(CommonString.KEY_DISPLAY,  listDataChild.get(listDataHeader.get(i)).get(j).getDisplay());
                        values1.put(CommonString.KEY_VISIT_DATE, visit_date);
                        values1.put(CommonString.KEY_USER_ID, username);
                        values1.put(CommonString.KEY_STORE_ID, store_id);
                        values1.put(CommonString.KEY_PAID_VISIBILITY_PRESENT, listDataChild.get(listDataHeader.get(i)).get(j).getPresent());
                        values1.put(CommonString.KEY_BRAND_ID,listDataHeader.get(i).getBrandId());
                        values1.put(CommonString.KEY_REASON_ID, listDataChild.get(listDataHeader.get(i)).get(j).getReasonId());
                        values1.put(CommonString.KEY_IMAGE1, listDataChild.get(listDataHeader.get(i)).get(j).getImage1());
                        values1.put(CommonString.KEY_IMAGE2, listDataChild.get(listDataHeader.get(i)).get(j).getImage2());

                        l = db.insert(CommonString.TABLE_PAID_VISIBILITY_CHILD_DATA, null, values1);
                    }
                }
            }

            if (l > 0) {
                return l;
            } else {
                return 0;
            }
        } catch (Exception ex) {

            return 0;
        }
    }



    public List<BrandMaster> getSavedPaidVisibilityHeaderData(String store_id, String visit_date) {
        ArrayList<BrandMaster> list = new ArrayList<>();
        Cursor dbcursor = null;
        try {

            dbcursor = db.rawQuery("select * from " + CommonString.TABLE_PAID_VISIBILITY_HEADER_DATA + " where " + CommonString.KEY_STORE_ID + " = '" + store_id + "' and " + CommonString.KEY_VISIT_DATE + " = '" + visit_date + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    BrandMaster sb1 = new BrandMaster();
                    sb1.setBrand(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_BRAND)));
                    sb1.setBrandId(dbcursor.getInt(dbcursor.getColumnIndexOrThrow(CommonString.KEY_BRAND_ID)));
                    sb1.setStore_id(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
                    list.add(sb1);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching", e.toString());
            return list;
        }

        Log.d("Fetching non working", "-------------------");
        return list;
    }


    public ArrayList<BrandMaster> getSavedPaidVisibilityInsertedChildData(Integer brandId, String store_id, String visit_date) {
        ArrayList<BrandMaster> list = new ArrayList<>();
        Cursor dbcursor = null, dbcursor2 = null;
        String promotion_id = "";
        try {

            dbcursor = db.rawQuery("select * from " + CommonString.TABLE_PAID_VISIBILITY_CHILD_DATA + " where " + CommonString.KEY_STORE_ID + " = '" + store_id + "' and " + CommonString.KEY_VISIT_DATE + " = '" + visit_date + "' and " + CommonString.KEY_BRAND_ID + " = '" + brandId + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    BrandMaster sb1 = new BrandMaster();
                    sb1.setDisplay_Id(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_DISPLAY_ID)));
                    sb1.setPresent(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_PAID_VISIBILITY_PRESENT)));
                    sb1.setImage1(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_IMAGE1)));
                    sb1.setBrandId(dbcursor.getInt(dbcursor.getColumnIndexOrThrow(CommonString.KEY_BRAND_ID)));
                    sb1.setImage2(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_IMAGE2)));
                    sb1.setReasonId(dbcursor.getInt(dbcursor.getColumnIndexOrThrow(CommonString.KEY_REASON_ID)));
                    list.add(sb1);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching", e.toString());
            return list;
        }

        Log.d("Fetching non working", "-------------------");
        return list;
    }

    public ArrayList<BrandMaster> getDisplayData(Integer brandId, JourneyPlan journeyPlan) {
        ArrayList<BrandMaster> list = new ArrayList<>();
        Cursor dbcursor = null;
        try {

            dbcursor = db.rawQuery("select distinct DM.Display_Id,DM.Display from Mapping_Paid_Visibility MPV " +
                    "inner join Display_Master DM ON MPV.Display_Id = DM.Display_Id " +
                    "where MPV.Brand_Id ='" + brandId + "' " +
                    "and MPV.Store_Id = '" + journeyPlan.getStoreId() + "' ", null);


            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    BrandMaster sb1 = new BrandMaster();
                    sb1.setDisplay(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Display")));
                    sb1.setDisplay_Id(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Display_Id")));
                    list.add(sb1);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching", e.toString());
            return list;
        }

        Log.d("Fetching non working", "-------------------");
        return list;
    }


    public boolean isPaidVisibilityFilledData(Integer storeId) {
        boolean filled = false;
        Cursor dbcursor = null;
        try {

            dbcursor = db.rawQuery("SELECT  STORE_ID " + "FROM " + CommonString.TABLE_PAID_VISIBILITY_HEADER_DATA + " WHERE STORE_ID = '" + storeId + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    if (dbcursor.getString(dbcursor.getColumnIndexOrThrow("STORE_ID")) == null || dbcursor.getString(dbcursor.getColumnIndexOrThrow("STORE_ID")).equals("")) {
                        filled = false;
                        break;
                    } else {
                        filled = true;
                    }
                    dbcursor.moveToNext();
                }
                dbcursor.close();
            }

        } catch (Exception e) {
            Log.d("Exception ", "when fetching Records!!!!!!!!!!!!!!!!!!!!!" + e.toString());
            return filled;
        }

        return filled;
    }


    public ArrayList<BrandMaster> getSavedPaidVisibilityInsertedChildData( String store_id, String visit_date) {
        ArrayList<BrandMaster> list = new ArrayList<>();
        Cursor dbcursor = null, dbcursor2 = null;
        String promotion_id = "";
        try {

            dbcursor = db.rawQuery("select * from " + CommonString.TABLE_PAID_VISIBILITY_CHILD_DATA + " where " + CommonString.KEY_STORE_ID + " = '" + store_id + "' and " + CommonString.KEY_VISIT_DATE + " = '" + visit_date + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    BrandMaster sb1 = new BrandMaster();
                    sb1.setDisplay_Id(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_DISPLAY_ID)));
                    sb1.setPresent(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_PAID_VISIBILITY_PRESENT)));
                    sb1.setImage1(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_IMAGE1)));
                    sb1.setBrandId(dbcursor.getInt(dbcursor.getColumnIndexOrThrow(CommonString.KEY_BRAND_ID)));
                    sb1.setImage2(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_IMAGE2)));
                    sb1.setReasonId(dbcursor.getInt(dbcursor.getColumnIndexOrThrow(CommonString.KEY_REASON_ID)));
                    list.add(sb1);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching", e.toString());
            return list;
        }

        Log.d("Fetching non working", "-------------------");
        return list;
    }


    public ArrayList<CategoryMaster> getSavedPromotionInsertedChildData( String store_id, String visit_date) {
        ArrayList<CategoryMaster> list = new ArrayList<>();
        Cursor dbcursor = null, dbcursor2 = null;
        String promotion_id = "";
        try {

            dbcursor = db.rawQuery("select * from " + CommonString.TABLE_PROMOTION_CHILD_DATA + " where " + CommonString.KEY_STORE_ID + " = '" + store_id + "' and " + CommonString.KEY_VISIT_DATE + " = '" + visit_date + "'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    CategoryMaster sb1 = new CategoryMaster();
                    promotion_id = dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_PROMOTION_ID));
                    sb1.setId(promotion_id);
                    sb1.setPromotion(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_PROMOTION)));
                    sb1.setPresent(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_PROMOTION_PRESENT)));
                    sb1.setImage1(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_IMAGE1)));
                    sb1.setCategoryId(dbcursor.getInt(dbcursor.getColumnIndexOrThrow(CommonString.KEY_CATEGORY_ID)));
                    sb1.setImage2(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_IMAGE2)));
                    sb1.setReasonId(dbcursor.getInt(dbcursor.getColumnIndexOrThrow(CommonString.KEY_REASON_ID)));
                    list.add(sb1);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception when fetching", e.toString());
            return list;
        }

        Log.d("Fetching non working", "-------------------");
        return list;
    }




    public boolean issampledDataFilled(String storeId) {
        boolean filled = false;

        Cursor dbcursor = null;

        try {
            dbcursor = db.rawQuery("SELECT * FROM SAMPLED_DATA WHERE STORE_ID= '" + storeId + "'", null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                int icount = dbcursor.getInt(0);
                dbcursor.close();
                if (icount > 0) {
                    filled = true;
                } else {
                    filled = false;
                }

            }

        } catch (Exception e) {

            return filled;
        }

        return filled;
    }


    public boolean isTesterStockFilled(String storeId) {
        boolean filled = false;

        Cursor dbcursor = null;

        try {
            dbcursor = db.rawQuery("SELECT * FROM "+CommonString.TABLE_TESTER_STOCK_DATA+" WHERE STORE_ID= '" + storeId + "'", null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                int icount = dbcursor.getInt(0);
                dbcursor.close();
                if (icount > 0) {
                    filled = true;
                } else {
                    filled = false;
                }

            }

        } catch (Exception e) {

            return filled;
        }

        return filled;
    }



    public void removealSamplingData(JourneyPlan jcp) {
        db.delete(CommonString.TABLE_INSERT_SAMPLED_DATA, CommonString.KEY_STORE_ID + "='" + jcp.getStoreId() + "'", null);
        db.delete(CommonString.TABLE_INSERT_SAMPLED_CHCEKLIST_DATA, CommonString.KEY_STORE_ID + "='" + jcp.getStoreId() + "'", null);
    }


    public boolean insertSamplingCheckListData(SamplingChecklistGetterSetter samplingChecklistGetterSetter) {
        db.delete("Sampling_Checklist", null, null);
        ContentValues values = new ContentValues();
        List<SamplingChecklist> data = samplingChecklistGetterSetter.getSamplingChecklist();
        try {
            if (data.size() == 0) {
                return false;
            }

            for (int i = 0; i < data.size(); i++) {

                values.put("Sampling_Checklist_Id", data.get(i).getSamplingChecklistId());
                values.put("Sampling_Checklist", data.get(i).getSamplingChecklist());
                values.put("Sampling_Answer_Id", data.get(i).getSamplingAnswerId());
                values.put("Answer_Type", data.get(i).getAnswerType());
                values.put("Sampling_Answer", data.get(i).getSamplingAnswer());
                values.put("Enable_Disable", data.get(i).getEnableDisable());

                long id = db.insert("Sampling_Checklist", null, values);
                if (id == -1) {
                    throw new Exception();
                }
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.d("Database Exception  ", ex.toString());
            return false;
        }
    }

    public ArrayList<SamplingChecklist> getSamplingCheckListData() {
        ArrayList<SamplingChecklist> list = new ArrayList<>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("select distinct Sampling_Checklist_Id,Sampling_Checklist,Answer_Type from Sampling_Checklist", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    SamplingChecklist sb = new SamplingChecklist();
                    sb.setSamplingChecklistId(dbcursor.getInt(dbcursor.getColumnIndexOrThrow("Sampling_Checklist_Id")));
                    sb.setSamplingChecklist(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Sampling_Checklist")));
                    sb.setAnswerType(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Answer_Type")));
                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {
            return list;
        }
        return list;
    }

    public ArrayList<SamplingChecklist> getSamplingChecklistAnswerData(Integer samplingChecklistId, int flag) {
        ArrayList<SamplingChecklist> list = new ArrayList<>();
        Cursor dbcursor = null;
        try {

            if(flag == 0) {
                SamplingChecklist sb = new SamplingChecklist();
                sb.setEnableDisable(-1);
                sb.setSamplingAnswerId(0);
                sb.setSamplingAnswer("Select");
                list.add(sb);
            }

            dbcursor = db.rawQuery("select distinct Sampling_Answer_Id,Sampling_Answer,Enable_Disable,Answer_Type from Sampling_Checklist where Sampling_Checklist_Id = '"+samplingChecklistId+"' ", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    SamplingChecklist sb1 = new SamplingChecklist();
                    sb1.setSamplingAnswerId(dbcursor.getInt(dbcursor.getColumnIndexOrThrow("Sampling_Answer_Id")));
                    sb1.setSamplingAnswer(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Sampling_Answer")));
                    sb1.setEnableDisable(dbcursor.getInt(dbcursor.getColumnIndexOrThrow("Enable_Disable")));
                    sb1.setAnswerType(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Answer_Type")));

                    list.add(sb1);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {
            return list;
        }
        return list;
    }

    public ArrayList<SamplingChecklist> getInsertedSamplingData(String storeId, String key_id) {
        Log.d("Fetching", "Storedata--------------->Start<------------");

        ArrayList<SamplingChecklist> list = new ArrayList<>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("Select * from SAMPLED_CHECKLIST_DATA where STORE_ID='" + storeId + "' and COMMON_ID = '"+key_id+"'", null);
            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    SamplingChecklist sb = new SamplingChecklist();
                    sb.setSampling_Correct_Answer(dbcursor.getString(dbcursor.getColumnIndexOrThrow("CHCEKLIST_ANSWER")));
                    sb.setSampling_Correct_Answer_Cd(dbcursor.getInt(dbcursor.getColumnIndexOrThrow("CHCEKLIST_CORRECT_ANSWER_CD")));
                    sb.setSamplingChecklistId(dbcursor.getInt(dbcursor.getColumnIndexOrThrow("CHCEKLIST_ID")));

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {
            return list;
        }
        return list;
    }


    public ArrayList<StockNewGetterSetter> getstockInsertedStoreDetails(JourneyPlan jcp, HashMap<StockNewGetterSetter, List<StockNewGetterSetter>> data, List<StockNewGetterSetter> save_listDataHeader) {
        Log.d("Fetching", "Storedata--------------->Start<------------");
        ArrayList<StockNewGetterSetter> list = new ArrayList<StockNewGetterSetter>();
        String skuId = "",closing_stock = "";
        Cursor dbcursor = null;

        try {

            ArrayList<HeaderGetterSetter> list1 = new ArrayList<>();
            list1 = getHeaderStock(jcp.getStoreId());

            dbcursor = db.rawQuery("Select * from "+CommonString.TABLE_STOCK_DATA+" where STORE_ID = "+jcp.getStoreId()+"", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    StockNewGetterSetter sb = new StockNewGetterSetter();
                    sb.setSku(dbcursor.getString(dbcursor.getColumnIndexOrThrow("SKU")));
                    sb.setEd_midFacing(dbcursor.getString(dbcursor.getColumnIndexOrThrow("MIDDAY_STOCK")));
                    sb.setOpening_facing(dbcursor.getString(dbcursor.getColumnIndexOrThrow("OPENING_STOCK")));
                    skuId =  dbcursor.getString(dbcursor.getColumnIndexOrThrow("SKU_CD"));

                    for (int i = 0; i < list1.size(); i++) {
                        for (int j = 0; j < data.get(save_listDataHeader.get(i)).size(); j++) {
                            if(data.get(save_listDataHeader.get(i)).get(j).getSku_cd().equalsIgnoreCase(skuId)) {
                                closing_stock =   data.get(save_listDataHeader.get(i)).get(j).getEd_closingFacing();
                                break;
                            }
                        }
                    }

                    sb.setClosing_stock(closing_stock);

                    list.add(sb);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }
        } catch (Exception e) {
            Log.d("Exception", " when fetching opening stock!!!!!!!!!!! " + e.toString());
            return list;
        }

        Log.d("Fetching", " opening stock---------------------->Stop<-----------");
        return list;
    }


    public boolean insertPerformancedata(PerformancePageGetterSetter data) {
        db.delete("Performance", null, null);
        List<PerformancePage> performance = data.getPerformancePage();
        ContentValues values = new ContentValues();
        try {
            if (performance.size() == 0) {
                return false;
            }
            for (int i = 0; i < performance.size(); i++) {
                values.put("USER_ID", performance.get(i).getUSERID());
                values.put("Performance_Page", performance.get(i).getPerformancePage());

                long id = db.insert("Performance", null, values);
                if (id == -1) {
                    throw new Exception();
                }
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.d("Exception in performance page", ex.toString());
            return false;
        }
    }

    public boolean insertMappingTesterData(MappingTesterStockGetterSetter data) {
        db.delete("Mapping_Tester_Stock", null, null);
        List<MappingTesterStock> stockData = data.getMappingTesterStock();
        ContentValues values = new ContentValues();
        try {
            if (stockData.size() == 0) {
                return false;
            }
            for (int i = 0; i < stockData.size(); i++) {
                values.put("State_Id", stockData.get(i).getStateId());
                values.put("Sku_Id", stockData.get(i).getSkuId());
                values.put("Sku", stockData.get(i).getSku());
                values.put("Store_Category_Id", stockData.get(i).getStoreCategoryId());
                values.put("Store_Type_Id", stockData.get(i).getStoreTypeId());

                long id = db.insert("Mapping_Tester_Stock", null, values);
                if (id == -1) {
                    throw new Exception();
                }
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.d("Exception in mapping tester stock", ex.toString());
            return false;
        }
    }

    public PerformancePage getPerformancePage() {
        PerformancePage list = new PerformancePage();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("Select * from Performance", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    list.setPerformancePage(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Performance_Page")));
                    list.setUSERID(dbcursor.getString(dbcursor.getColumnIndexOrThrow("User_Id")));
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception Performance",
                    e.toString());
            return list;
        }
        return list;
    }

    public ArrayList<MappingTesterStock> getTesterStockData(Integer storeTypeId, String stateId, Integer storeCategoryId) {
        ArrayList<MappingTesterStock> list = new ArrayList<>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("Select * from Mapping_Tester_Stock where State_Id = '"+stateId+"' and Store_Category_Id = '"+ storeCategoryId +"' and Store_Type_Id = '"+storeTypeId+"'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    MappingTesterStock mappingTesterStock = new MappingTesterStock();
                    mappingTesterStock.setSku(dbcursor.getString(dbcursor.getColumnIndexOrThrow("Sku")));
                    mappingTesterStock.setSkuId(dbcursor.getInt(dbcursor.getColumnIndexOrThrow("Sku_Id")));
                    list.add(mappingTesterStock);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception tester stock",
                    e.toString());
            return list;
        }
        return list;
    }

    public long insertTesterStockData(ArrayList<MappingTesterStock> testerStockList, String store_id, String menu_id, String visit_date, String username) {
        long id = 0;
        db.delete(CommonString.TABLE_TESTER_STOCK_DATA, "STORE_ID ='" + store_id + "' and "+CommonString.KEY_VISIT_DATE+" = '"+visit_date+"'", null);

        ContentValues values;
        try {

            for (int i = 0; i < testerStockList.size(); i++) {
                values = new ContentValues();
                values.put(CommonString.KEY_VISIT_DATE, visit_date);
                values.put(CommonString.KEY_USERNAME, username);
                values.put(CommonString.KEY_STORE_ID, store_id);
                values.put(CommonString.KEY_MENU_ID, menu_id);
                values.put(CommonString.KEY_SKU, testerStockList.get(i).getSku());
                values.put(CommonString.KEY_SKU_ID, testerStockList.get(i).getSkuId());
                values.put(CommonString.KEY_TESTER_STOCK_EXIST, testerStockList.get(i).getIsChecked());

                id = db.insert(CommonString.TABLE_TESTER_STOCK_DATA, null, values);
            }

            if (id > 0) {
                return id;
            } else {
                return 0;
            }
        } catch (Exception ex) {
            return 0;
        }
    }

    public ArrayList<MappingTesterStock> getInsertedTestStockData(String store_id, String visitDate) {
        ArrayList<MappingTesterStock> list = new ArrayList<>();
        Cursor dbcursor = null;
        try {
            dbcursor = db.rawQuery("Select * from "+CommonString.TABLE_TESTER_STOCK_DATA+" where "+CommonString.KEY_STORE_ID+" = '"+store_id+"' and "+CommonString.KEY_VISIT_DATE+" = '"+visitDate+"'", null);

            if (dbcursor != null) {
                dbcursor.moveToFirst();
                while (!dbcursor.isAfterLast()) {
                    MappingTesterStock mappingTesterStock = new MappingTesterStock();
                    mappingTesterStock.setStore_Id(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_STORE_ID)));
                    mappingTesterStock.setSku(dbcursor.getString(dbcursor.getColumnIndexOrThrow(CommonString.KEY_SKU)));
                    mappingTesterStock.setSkuId(dbcursor.getInt(dbcursor.getColumnIndexOrThrow(CommonString.KEY_SKU_ID)));
                    mappingTesterStock.setIsChecked(dbcursor.getInt(dbcursor.getColumnIndexOrThrow(CommonString.KEY_TESTER_STOCK_EXIST)));
                    list.add(mappingTesterStock);
                    dbcursor.moveToNext();
                }
                dbcursor.close();
                return list;
            }

        } catch (Exception e) {
            Log.d("Exception tester stock",
                    e.toString());
            return list;
        }
        return list;
    }
}
