package com.cpm.Marico.upload.Retrofit_method;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

import com.cpm.Marico.dailyEntry.StoreListActivity;
import com.cpm.Marico.database.MaricoDatabase;
import com.cpm.Marico.delegates.CoverageBean;
import com.cpm.Marico.getterSetter.BrandMaster;
import com.cpm.Marico.getterSetter.BrandMasterGetterSetter;
import com.cpm.Marico.getterSetter.CategoryMaster;
import com.cpm.Marico.getterSetter.CategoryMasterGetterSetter;
import com.cpm.Marico.getterSetter.DisplayMasterGetterSetter;
import com.cpm.Marico.getterSetter.FeedbackMasterGetterSetter;
import com.cpm.Marico.getterSetter.GeotaggingBeans;
import com.cpm.Marico.getterSetter.GroomingGetterSetter;
import com.cpm.Marico.getterSetter.JCPGetterSetter;
import com.cpm.Marico.getterSetter.JourneyPlan;
import com.cpm.Marico.getterSetter.MappingCategoryChecklistGetterSetter;
import com.cpm.Marico.getterSetter.MappingFocusSkuGetterSetter;
import com.cpm.Marico.getterSetter.MappingInitiativeGetterSetter;
import com.cpm.Marico.getterSetter.MappingMenuGetterSetter;
import com.cpm.Marico.getterSetter.MappingPaidVisibilityGetterSetter;
import com.cpm.Marico.getterSetter.MappingPosmGetterSetter;
import com.cpm.Marico.getterSetter.MappingPromotionGetterSetter;
import com.cpm.Marico.getterSetter.MappingSamplingGetterSetter;
import com.cpm.Marico.getterSetter.MappingShareOfShelfGetterSetter;
import com.cpm.Marico.getterSetter.MappingTesterStock;
import com.cpm.Marico.getterSetter.MappingTesterStockGetterSetter;
import com.cpm.Marico.getterSetter.MappingVisicoolerGetterSetter;
import com.cpm.Marico.getterSetter.MarketIntelligenceGetterSetter;
import com.cpm.Marico.getterSetter.MenuMaster;
import com.cpm.Marico.getterSetter.MenuMasterGetterSetter;
import com.cpm.Marico.getterSetter.NonCategoryReasonGetterSetter;
import com.cpm.Marico.getterSetter.NonExecutionReasonGetterSetter;
import com.cpm.Marico.getterSetter.NonWindowReasonGetterSetter;
import com.cpm.Marico.getterSetter.NonWorkingReasonGetterSetter;
import com.cpm.Marico.getterSetter.PerformancePageGetterSetter;
import com.cpm.Marico.getterSetter.PosmMasterGetterSetter;
import com.cpm.Marico.getterSetter.PromoterSkuwiseSaleGetterSetter;
import com.cpm.Marico.getterSetter.PromoterTDPSaleTargetGetterSetter;
import com.cpm.Marico.getterSetter.PromoterTargetGetterSetter;
import com.cpm.Marico.getterSetter.ReferenceVariablesForDownloadActivity;
import com.cpm.Marico.getterSetter.SampledGetterSetter;
import com.cpm.Marico.getterSetter.SamplingChecklist;
import com.cpm.Marico.getterSetter.SamplingChecklistGetterSetter;
import com.cpm.Marico.getterSetter.ShareOfShelfGetterSetter;
import com.cpm.Marico.getterSetter.SkuMasterGetterSetter;
import com.cpm.Marico.getterSetter.StockNewGetterSetter;
import com.cpm.Marico.getterSetter.StoreProfileGetterSetter;
import com.cpm.Marico.getterSetter.StoreTypeMasterGetterSetter;
import com.cpm.Marico.getterSetter.SubCategoryMasterGetterSetter;
import com.cpm.Marico.getterSetter.TableStructure;
import com.cpm.Marico.getterSetter.TableStructureGetterSetter;
import com.cpm.Marico.getterSetter.WindowCheckAnswerGetterSetter;
import com.cpm.Marico.getterSetter.WindowChecklistGetterSetter;
import com.cpm.Marico.upload.Retrofit_method.upload.ToStringConverterFactory;
import com.cpm.Marico.utilities.AlertandMessages;
import com.cpm.Marico.utilities.CommonString;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by upendra on 15/02/2019.
 */

public class UploadImageWithRetrofit extends ReferenceVariablesForDownloadActivity {

    boolean isvalid;
    RequestBody body1;
    private Retrofit adapter;
    Context context;
    public int totalFiles = 0;
    public static int uploadedFiles = 0;
    public int listSize = 0;
    int status = 0;
    MaricoDatabase db;
    ProgressDialog pd;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    String _UserId, date, app_ver, rightname;
    String[] jj;
    boolean statusUpdated = true;
    int from;

    public UploadImageWithRetrofit(Context context) {
        this.context = context;
    }

    public UploadImageWithRetrofit(Context context, String progessTitle, String progressStr, MaricoDatabase db) {
        this.context = context;
        this.db = db;
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        _UserId = preferences.getString(CommonString.KEY_USERNAME, "");
        date = preferences.getString(CommonString.KEY_DATE, "");
        pd = new ProgressDialog(this.context);
        pd.setTitle(progessTitle);
        pd.setMessage(progressStr);
        pd.setCancelable(false);
        if (pd != null && (!pd.isShowing())) {
            pd.show();
        }
    }

    public UploadImageWithRetrofit(Context context, MaricoDatabase db, ProgressDialog pd, int from) {
        this.context = context;
        this.db = db;
        this.pd = pd;
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = preferences.edit();
        this.from = from;
        _UserId = preferences.getString(CommonString.KEY_USERNAME, "");
        date = preferences.getString(CommonString.KEY_DATE, null);
        rightname = preferences.getString(CommonString.KEY_RIGHTNAME, null);
        try {
            app_ver = String.valueOf(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName);
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        db.open();
    }

    public String downloadDataUniversal(final String jsonString, int type) {
        try {
            status = 0;
            isvalid = false;
            final String[] data_global = {""};
            RequestBody jsonData = RequestBody.create(MediaType.parse("application/json"), jsonString);

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(20, TimeUnit.SECONDS)
                    .writeTimeout(20, TimeUnit.SECONDS)
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .build();

            adapter = new Retrofit.Builder()
                    .baseUrl(CommonString.URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
            PostApi api = adapter.create(PostApi.class);
            Call<ResponseBody> call = null;
            if (type == CommonString.LOGIN_SERVICE) {
                call = api.getLogindetail(jsonData);
            } else if (type == CommonString.DOWNLOAD_ALL_SERVICE) {
                call = api.getDownloadAll(jsonData);
            } else if (type == CommonString.COVERAGE_DETAIL) {
                call = api.getCoverageDetail(jsonData);
            } else if (type == CommonString.COVERAGE_DETAIL_CLIENT) {
                call = api.getCoverageDetailClient(jsonData);
            } else if (type == CommonString.UPLOADJCPDetail) {
                call = api.getUploadJCPDetail(jsonData);
            } else if (type == CommonString.UPLOADJsonDetail) {
                call = api.getUploadJsonDetail(jsonData);
            } else if (type == CommonString.COVERAGEStatusDetail) {
                call = api.getCoverageStatusDetail(jsonData);
            } else if (type == CommonString.CHECKOUTDetail) {
                call = api.getCheckout(jsonData);
            } else if (type == CommonString.CHECKOUTDetail_CLIENT) {
                call = api.getCheckoutClient(jsonData);
            } else if (type == CommonString.DELETE_COVERAGE) {
                call = api.deleteCoverageData(jsonData);
            } else if (type == CommonString.COVERAGE_NONWORKING) {
                call = api.setCoverageNonWorkingData(jsonData);
            } else if (type == CommonString.CHANGE_PASSWORD_SERVICE) {
                call = api.setNewPassword(jsonData);
            }


            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    ResponseBody responseBody = response.body();
                    String data = null;
                    if (responseBody != null && response.isSuccessful()) {
                        try {
                            data = response.body().string();
                            if (data.equalsIgnoreCase("")) {
                                data_global[0] = "";
                                isvalid = true;
                                status = 1;
                            } else {
                                data = data.substring(1, data.length() - 1).replace("\\", "");
                                data_global[0] = data;
                                isvalid = true;
                                status = 1;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            isvalid = true;
                            status = -2;
                        }
                    } else {
                        isvalid = true;
                        status = -1;
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    isvalid = true;
                    if (t instanceof SocketTimeoutException) {
                        status = 3;
                    } else if (t instanceof IOException) {
                        status = 3;
                    } else {
                        status = 3;
                    }

                }
            });

            while (isvalid == false) {
                synchronized (this) {
                    this.wait(25);
                }
            }
            if (isvalid) {
                synchronized (this) {
                    this.notify();
                }
            }
            if (status == 1) {
                return data_global[0];
            } else if (status == 2) {
                return CommonString.MESSAGE_NO_RESPONSE_SERVER;
            } else if (status == 3) {
                return CommonString.MESSAGE_SOCKETEXCEPTION;
            } else if (status == -2) {
                return CommonString.MESSAGE_INVALID_JSON;
            } else {
                return CommonString.KEY_FAILURE;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return CommonString.KEY_FAILURE;
        }
    }


    public File saveBitmapToFileSmaller(File file) {
        File file2 = file;
        try {
            int inWidth = 0;
            int inHeight = 0;

            InputStream in = new FileInputStream(file2);
            // decode image size (decode metadata only, not the whole image)
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(in, null, options);
            in.close();
            in = null;

            // save width and height
            inWidth = options.outWidth;
            inHeight = options.outHeight;

            // decode full image pre-resized
            in = new FileInputStream(file2);
            options = new BitmapFactory.Options();
            // calc rought re-size (this is no exact resize)
            options.inSampleSize = Math.max(inWidth / 800, inHeight / 500);
            // decode full image
            Bitmap roughBitmap = BitmapFactory.decodeStream(in, null, options);

            // calc exact destination size
            Matrix m = new Matrix();
            RectF inRect = new RectF(0, 0, roughBitmap.getWidth(), roughBitmap.getHeight());
            RectF outRect = new RectF(0, 0, 800, 500);
            m.setRectToRect(inRect, outRect, Matrix.ScaleToFit.CENTER);
            float[] values = new float[9];
            m.getValues(values);
            // resize bitmap
            Bitmap resizedBitmap = Bitmap.createScaledBitmap(roughBitmap, (int) (roughBitmap.getWidth() * values[0]), (int) (roughBitmap.getHeight() * values[4]), true);
            // save image
            FileOutputStream out = new FileOutputStream(file2);
            resizedBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);

        } catch (Exception e) {
            Log.e("Image", e.toString(), e);
            return file;
        }
        return file2;
    }


    public void UploadImageRecursive(final Context context, final String coverageDate) {
        try {
            String filename = null, foldername = null;
            int totalfiles = 0;
            File f = new File(CommonString.FILE_PATH);
            File file[] = f.listFiles();
            if (file.length > 0) {
                filename = "";
                totalfiles = f.listFiles().length;
                for (int i = 0; i < file.length; i++) {
                    if (new File(CommonString.FILE_PATH + file[i].getName()).exists()) {
                        if (file[i].getName().contains("_StoreImg-")) {
                            foldername = "CoverageImages";
                        } else if (file[i].getName().contains("_NONWORKING-")) {
                            foldername = "CoverageImages";
                        } else if (file[i].getName().contains("_GeoTag-")) {
                            foldername = "GeoTagImages";
                        } else if (file[i].getName().contains("_OPENING_STOCK_IMAGE_")) {
                            foldername = "StockImages";
                        } else if (file[i].getName().contains("_SHARE_OF_SHELF_IMAGE_")) {
                            foldername = "SOSImages";
                        } else if (file[i].getName().contains("_ADDITIONAL_VIS_")) {
                            foldername = "AdditionalVisibilityImages";
                        } else if (file[i].getName().contains("_STORE_GROOMING_IMG_")) {
                            foldername = "GroomingImages";
                        } else if (file[i].getName().contains("_Promotion_Image1") || file[i].getName().contains("_Promotion_Image2")) {
                            foldername = "PromotionImages";
                        } else if (file[i].getName().contains("_Paid_Visibility_Image1") || file[i].getName().contains("_Paid_Visibility_Image2")) {
                            foldername = "PaidVisibilityImages";
                        } else {
                            foldername = "BulkImages";
                        }
                        filename = file[i].getName();
                    }
                    break;
                }


                status = 0;
                File originalFile = new File(CommonString.FILE_PATH + filename);
                final File finalFile = saveBitmapToFileSmaller(originalFile);
                String date;
                if (false) {
                    date = getParsedDate(filename);
                } else {
                    date = this.date;
                }
                isvalid = false;

                OkHttpClient.Builder b = new OkHttpClient.Builder();
                b.connectTimeout(20, TimeUnit.SECONDS);
                b.readTimeout(20, TimeUnit.SECONDS);
                b.writeTimeout(20, TimeUnit.SECONDS);
                OkHttpClient client = b.build();
                pd.setMessage("uploading images (" + uploadedFiles + "/" + totalFiles + ")");
                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), finalFile);
                //com.squareup.okhttp3.RequestBody requestFile = com.squareup.okhttp.RequestBody.create(com.squareup.okhttp.MediaType.parse("application/octet-stream"), finalFile);
                // MultipartBody.Part body = MultipartBody.Part.createFormData("file", filename, requestFile);
                // MultipartBody.Part body = MultipartBody.Part.createFormData("file", filename, requestFile).createFormData("Foldername", foldername);
                //  RequestBody name = RequestBody.create(MediaType.parse("application/octet-stream"), "upload_test");
                // add another part within the multipart request
                body1 = new MultipartBody.Builder()
                        .setType(MediaType.parse("multipart/form-data"))
                        .addFormDataPart("file", finalFile.getName(), requestFile)
                        .addFormDataPart("Foldername", foldername)
                        .addFormDataPart("Path", date)
                        .build();

                /*body1 = new MultipartBuilder()
                        .type(MultipartBuilder.FORM)
                        .addFormDataPart("file", finalFile.getName(), requestFile)
                        .addFormDataPart("FolderName", foldername)
                        .build();*/
                adapter = new Retrofit.Builder()
                        .baseUrl(CommonString.URL3)
                        .addConverterFactory(new ToStringConverterFactory())
                        .client(client)
                        .build();

                PostApi api = adapter.create(PostApi.class);
                Call<String> observable = api.getUploadImage(body1);
                observable.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful() && response.body().contains("Success")) {
                            finalFile.delete();
                            uploadedFiles++;
                            status = 1;
                        } else {
                            status = 0;
                        }
                        if (status == 0) {
                            pd.dismiss();
                            if (!((Activity) context).isFinishing()) {
                                AlertandMessages.showAlert((Activity) context, "Image not uploaded." + "\n" + uploadedFiles + " images uploaded out of " + totalFiles, true);
                            }
                        } else {
                            UploadImageRecursive(context, coverageDate);
                        }

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        if (t instanceof IOException || t instanceof SocketTimeoutException || t instanceof SocketException) {
                            status = -1;
                            //uploadedFiles = 0;
                            pd.dismiss();
                            // AlertandMessages.showAlert((Activity) context, "Network Error in upload", false);
                            if (!((Activity) context).isFinishing()) {
                                AlertandMessages.showAlert((Activity) context, "Network Error in upload." + "\n" + uploadedFiles + " images uploaded out of " + totalFiles, true);
                            } else {

                            }
                        }

                    }
                });

            } else {
                if (totalFiles == uploadedFiles) {
                    //region Coverage upload status Data
                    new StatusUpload(coverageDate).execute();
                    //endregion
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    String getParsedDate(String filename) {
        String testfilename = filename;
        testfilename = testfilename.substring(testfilename.indexOf("-") + 1);
        testfilename = testfilename.substring(0, testfilename.indexOf("-"));
        return testfilename;
    }

    public void uploadDataWithoutWait(final ArrayList<String> keyList, final int keyIndex, final ArrayList<CoverageBean> coverageList, final int coverageIndex) {

        try {
            status = 0;
            isvalid = false;
            final String[] data_global = {""};
            String jsonString = "";
            int type = 0;

            JSONObject jsonObject, jsonObject1, jsonObject4;

            //region Creating json data
            switch (keyList.get(keyIndex)) {
                case "CoverageDetail_latest":
                    //region Coverage Data
                    db.open();
                    jsonObject = new JSONObject();
                    jsonObject.put("StoreId", coverageList.get(coverageIndex).getStoreId());
                    jsonObject.put("VisitDate", coverageList.get(coverageIndex).getVisitDate());
                    jsonObject.put("Latitude", coverageList.get(coverageIndex).getLatitude());
                    jsonObject.put("Longitude", coverageList.get(coverageIndex).getLongitude());
                    jsonObject.put("ReasonId", coverageList.get(coverageIndex).getReasonid());
                    jsonObject.put("SubReasonId", "0");
                    jsonObject.put("Remark", "");
                    jsonObject.put("ImageName", coverageList.get(coverageIndex).getImage());
                    jsonObject.put("AppVersion", app_ver);
                    jsonObject.put("UploadStatus", CommonString.KEY_P);
                    jsonObject.put("Checkout_Image", coverageList.get(coverageIndex).getCkeckout_image());
                    jsonObject.put("UserId", coverageList.get(coverageIndex).getUserId());

                    jsonString = jsonObject.toString();
                    type = CommonString.COVERAGE_DETAIL;
                    //endregion
                    break;

                case "Store_Profile":
                    //region Coverage Data
                    db.open();
                    StoreProfileGetterSetter storePGT = db.getStoreProfileData(coverageList.get(coverageIndex).getStoreId(), coverageList.get(coverageIndex).getVisitDate());
                    if (storePGT.getStore_type_cd() != null && !storePGT.getStore_type_cd().equals("")) {
                        JSONArray storeDetail = new JSONArray();
                        jsonObject = new JSONObject();
                        jsonObject.put("MID", coverageList.get(coverageIndex).getMID());
                        jsonObject.put("UserId", coverageList.get(coverageIndex).getUserId());
                        jsonObject.put("Store_Type_cd", storePGT.getStore_type_cd());
                        jsonObject.put("Store_Name", storePGT.getStore_name());
                        jsonObject.put("Visit_date", storePGT.getStore_visit_date());
                        jsonObject.put("Address", storePGT.getStore_addres());
                        jsonObject.put("City", storePGT.getStore_city());
                        storeDetail.put(jsonObject);
                        jsonObject = new JSONObject();
                        jsonObject.put("MID", coverageList.get(coverageIndex).getMID());
                        jsonObject.put("Keys", "STORE_PROFILE_DATA");
                        jsonObject.put("JsonData", storeDetail.toString());
                        jsonObject.put("UserId", coverageList.get(coverageIndex).getUserId());
                        jsonString = jsonObject.toString();
                        type = CommonString.UPLOADJsonDetail;
                    }
                    //endregion
                    break;

                case "GeoTag":
                    //region GeoTag
                    ArrayList<GeotaggingBeans> geotaglist = db.getinsertGeotaggingData(coverageList.get(coverageIndex).getStoreId(), "N");
                    if (geotaglist.size() > 0) {
                        JSONArray topUpArray = new JSONArray();
                        for (int j = 0; j < geotaglist.size(); j++) {
                            JSONObject obj = new JSONObject();
                            obj.put(CommonString.KEY_STORE_ID, geotaglist.get(j).getStoreid());
                            obj.put(CommonString.KEY_VISIT_DATE, coverageList.get(coverageIndex).getVisitDate());
                            obj.put(CommonString.KEY_LATITUDE, geotaglist.get(j).getLatitude());
                            obj.put(CommonString.KEY_LONGITUDE, geotaglist.get(j).getLongitude());
                            obj.put("FRONT_IMAGE", geotaglist.get(j).getImage());
                            topUpArray.put(obj);
                        }

                        jsonObject = new JSONObject();
                        jsonObject.put("MID", coverageList.get(coverageIndex).getMID());
                        jsonObject.put("Keys", "GeoTag");
                        jsonObject.put("JsonData", topUpArray.toString());
                        jsonObject.put("UserId", coverageList.get(coverageIndex).getUserId());

                        jsonString = jsonObject.toString();
                        type = CommonString.UPLOADJsonDetail;
                    }
                    //endregion
                    break;

                //opening stock
                case "Stock_Data":
                    db.open();
                    JSONArray stockchildArray = new JSONArray();
                    JSONArray stockheaderArray = new JSONArray();
                    ArrayList<StockNewGetterSetter> stock_images = db.getStockImageUploadData(coverageList.get(coverageIndex).getStoreId().toString());
                    if (stock_images.size() > 0) {
                        for (int j = 0; j < stock_images.size(); j++) {

                            JSONObject obj = new JSONObject();
                            obj.put("MID", coverageList.get(coverageIndex).getMID());
                            obj.put("UserId", coverageList.get(coverageIndex).getUserId());
                            obj.put("Category_Id", stock_images.get(j).getCategory_cd());
                            obj.put("Category_Image", stock_images.get(j).getImg_cat_one());

                            ArrayList<StockNewGetterSetter> stock_data = db.getOpeningStockUpload(coverageList.get(coverageIndex).getStoreId());
                            for (int k = 0; k < stock_data.size(); k++) {
                                jsonObject1 = new JSONObject();
                                jsonObject1.put("MID", coverageList.get(coverageIndex).getMID());
                                jsonObject1.put("UserId", coverageList.get(coverageIndex).getUserId());
                                jsonObject1.put("Category_Id", stock_data.get(k).getCategory_cd());
                                jsonObject1.put("Sku_Id", stock_data.get(k).getSku_cd());
                                jsonObject1.put("Opening_Stock", stock_data.get(k).getStock1());
                                jsonObject1.put("MidDay_Stock", stock_data.get(k).getEd_midFacing());
                                jsonObject1.put("Closing_Stock", stock_data.get(k).getEd_closingFacing());
                                jsonObject1.put("Focus", stock_data.get(k).getFocus());

                                stockchildArray.put(jsonObject1);
                            }
                            stockheaderArray.put(obj);

                        }

                        jsonObject4 = new JSONObject();
                        jsonObject4.put("Stock_Image_Data", stockheaderArray);
                        jsonObject4.put("Stock_Data", stockchildArray);

                        jsonObject = new JSONObject();
                        jsonObject.put("MID", coverageList.get(coverageIndex).getMID());
                        jsonObject.put("Keys", "STOCK_DATA");
                        jsonObject.put("JsonData", jsonObject4.toString());
                        jsonObject.put("UserId", coverageList.get(coverageIndex).getUserId());

                        jsonString = jsonObject.toString();
                        type = CommonString.UPLOADJsonDetail;
                    }

                    break;

                case "SOS_DATA":
                    db.open();
                    JSONArray childArray = new JSONArray();
                    JSONArray headerArray = new JSONArray();
                    ArrayList<ShareOfShelfGetterSetter> listDataHeader1 = db.getHeaderShareOfSelfImgUploadData1(coverageList.get(coverageIndex).getStoreId(), coverageList.get(coverageIndex).getVisitDate());
                    if (listDataHeader1.size() > 0) {
                        for (int i = 0; i < listDataHeader1.size(); i++) {
                            jsonObject = new JSONObject();
                            jsonObject.put("MID", coverageList.get(coverageIndex).getMID());
                            jsonObject.put("UserId", _UserId);
                            jsonObject.put("Category_Id", listDataHeader1.get(i).getCategory_cd());
                            jsonObject.put("Sub_Category_Id", listDataHeader1.get(i).getSub_category_cd());
                            jsonObject.put("Image_Category_Facing", listDataHeader1.get(i).getImg_cat_facing());
                            jsonObject.put("KEY_Id", listDataHeader1.get(i).getKey_id());

                            ArrayList<ShareOfShelfGetterSetter> brandData = db.getShareofShelfBrandDataUpload(listDataHeader1.get(i).getKey_id(), coverageList.get(coverageIndex).getStoreId());
                            for (int j = 0; j < brandData.size(); j++) {
                                jsonObject1 = new JSONObject();
                                jsonObject1.put("MID", coverageList.get(coverageIndex).getMID());
                                jsonObject1.put("UserId", _UserId);
                                jsonObject1.put("Brand_Id", brandData.get(j).getBrand_cd());
                                jsonObject1.put("Facing", brandData.get(j).getFacing());
                                jsonObject1.put("KEY_Id", brandData.get(j).getKey_id());

                                childArray.put(jsonObject1);
                            }
                            headerArray.put(jsonObject);

                        }

                        jsonObject4 = new JSONObject();
                        jsonObject4.put("SOS_HEADER_DATA", headerArray);
                        jsonObject4.put("SOS_CHILD_DATA", childArray);

                        jsonObject = new JSONObject();
                        jsonObject.put("MID", coverageList.get(coverageIndex).getMID());
                        jsonObject.put("Keys", "SOS_DATA");
                        jsonObject.put("JsonData", jsonObject4.toString());
                        jsonObject.put("UserId", coverageList.get(coverageIndex).getUserId());

                        jsonString = jsonObject.toString();
                        type = CommonString.UPLOADJsonDetail;
                    }

                    break;
                case "Additional_Visibility_data":
                    db.open();
                    String valueex = "";
                    ArrayList<MarketIntelligenceGetterSetter> additionalVisibilityData = db.getPaidVisibilityUploadData(coverageList.get(coverageIndex).getStoreId().toString(), coverageList.get(coverageIndex).getVisitDate());
                    if (additionalVisibilityData.size() > 0) {
                        JSONArray compArray = new JSONArray();
                        for (int j = 0; j < additionalVisibilityData.size(); j++) {
                            boolean exist = additionalVisibilityData.get(j).isExists();
                            if (exist) {
                                valueex = "1";
                            } else {
                                valueex = "0";
                            }

                            JSONObject obj = new JSONObject();
                            obj.put("MID", coverageList.get(coverageIndex).getMID());
                            obj.put("UserId", _UserId);
                           // obj.put("Brand_Id", additionalVisibilityData.get(j).getCompany_cd());
                            obj.put("Display_Id", additionalVisibilityData.get(j).getCategory_cd());
                            obj.put("Photo", additionalVisibilityData.get(j).getPhoto());
                            obj.put("Remark", additionalVisibilityData.get(j).getRemark());
                            obj.put("Present", valueex);
                            compArray.put(obj);

                        }

                        jsonObject = new JSONObject();
                        jsonObject.put("MID", coverageList.get(coverageIndex).getMID());
                        jsonObject.put("Keys", "Additional_Visibility_data");
                        jsonObject.put("JsonData", compArray.toString());
                        jsonObject.put("UserId", coverageList.get(coverageIndex).getUserId());

                        jsonString = jsonObject.toString();
                        type = CommonString.UPLOADJsonDetail;
                    }
                    //endregion
                    break;

                case "Grooming":
                    //region Coverage Data
                    db.open();
                    GroomingGetterSetter groominggImg = db.getGroomigImgData(coverageList.get(coverageIndex).getStoreId(), coverageList.get(coverageIndex).getVisitDate());
                    if (groominggImg.getGrooming_image() != null && !groominggImg.getGrooming_image().equals("")) {
                        JSONArray storeDetail = new JSONArray();
                        jsonObject = new JSONObject();
                        jsonObject.put("MID", coverageList.get(coverageIndex).getMID());
                        jsonObject.put("UserId", coverageList.get(coverageIndex).getUserId());
                        jsonObject.put("StoreId", groominggImg.getStoreCd());
                        jsonObject.put("GroomingImage", groominggImg.getGrooming_image());
                        storeDetail.put(jsonObject);
                        jsonObject = new JSONObject();
                        jsonObject.put("MID", coverageList.get(coverageIndex).getMID());
                        jsonObject.put("Keys", "GroomingData");
                        jsonObject.put("JsonData", storeDetail.toString());
                        jsonObject.put("UserId", coverageList.get(coverageIndex).getUserId());
                        jsonString = jsonObject.toString();
                        type = CommonString.UPLOADJsonDetail;
                    }
                    //endregion
                    break;

                case "Paid_Visibility_Data":
                    //region Coverage Data
                    db.open();

                    List<BrandMaster> paidVisibilityData = db.getSavedPaidVisibilityInsertedChildData(coverageList.get(coverageIndex).getStoreId(), coverageList.get(coverageIndex).getVisitDate());

                    if (paidVisibilityData.size() > 0) {
                        JSONArray paidVisibilityArray = new JSONArray();
                        for (int i = 0; i < paidVisibilityData.size(); i++) {
                            jsonObject = new JSONObject();
                            jsonObject.put("MID", coverageList.get(coverageIndex).getMID());
                            jsonObject.put("User_Id", _UserId);
                            jsonObject.put(CommonString.KEY_STORE_ID, paidVisibilityData.get(i).getStore_id());
                            jsonObject.put(CommonString.KEY_BRAND_ID, paidVisibilityData.get(i).getBrandId());
                            jsonObject.put(CommonString.KEY_DISPLAY_ID, paidVisibilityData.get(i).getDisplay_Id());
                            jsonObject.put(CommonString.KEY_IMAGE1, paidVisibilityData.get(i).getImage1());
                            jsonObject.put(CommonString.KEY_IMAGE2, paidVisibilityData.get(i).getImage2());
                            jsonObject.put(CommonString.KEY_REASON_ID, paidVisibilityData.get(i).getReasonId());
                            jsonObject.put(CommonString.KEY_PAID_VISIBILITY_PRESENT, paidVisibilityData.get(i).getPresent());
                            paidVisibilityArray.put(jsonObject);
                        }

                        jsonObject = new JSONObject();
                        jsonObject.put("MID", coverageList.get(coverageIndex).getMID());
                        jsonObject.put("Keys", "Paid_Visibility_Data");
                        jsonObject.put("JsonData", paidVisibilityArray.toString());
                        jsonObject.put("UserId", coverageList.get(coverageIndex).getUserId());

                        jsonString = jsonObject.toString();
                        type = CommonString.UPLOADJsonDetail;
                    }
                    //endregion
                    break;


                case "Consumer_Connect_Data":

                    db.open();
                    List<SampledGetterSetter> sampling = db.getinsertedsampledData(coverageList.get(coverageIndex).getStoreId(), coverageList.get(coverageIndex).getVisitDate());

                    String valuesampling = "";
                    if (sampling.size() > 0) {
                        JSONObject jsonData = new JSONObject();
                        JSONObject jsonObject2 = new JSONObject();
                        JSONArray samplingData = new JSONArray();
                       // JSONArray samplingData2 = new JSONArray();
                        for (int i = 0; i < sampling.size(); i++) {
                            JSONArray samplingData1 = new JSONArray();
                            boolean exist = sampling.get(i).isExists();
                            if (exist) {
                                valuesampling = "1";
                            } else {
                                valuesampling = "0";
                            }
                            jsonObject2 = new JSONObject();
                            jsonObject2.put("MID", coverageList.get(coverageIndex).getMID());
                            jsonObject2.put("User_Id", _UserId);
                            jsonObject2.put("Mobile", sampling.get(i).getMobile());
                            jsonObject2.put("Email", sampling.get(i).getEmail());
                            jsonObject2.put("Name", sampling.get(i).getName());
                            jsonObject2.put("Present", valuesampling);
                            jsonObject2.put("Id", sampling.get(i).getKey_id());

                            List<SamplingChecklist> sampleData = db.getInsertedSamplingData(coverageList.get(coverageIndex).getStoreId(),sampling.get(i).getKey_id());

                            for(int k=0;k<sampleData.size();k++){
                                JSONObject jsonObj = new JSONObject();
                                jsonObj.put("Id", sampling.get(i).getKey_id());
                                jsonObj.put("MID", coverageList.get(coverageIndex).getMID());
                                jsonObj.put("Answer", sampleData.get(k).getSampling_Correct_Answer());
                                jsonObj.put("Checklist_cd", sampleData.get(k).getSamplingChecklistId());
                                samplingData1.put(jsonObj);
                            }

                            jsonObject2.put("Consumer_connect_checkList_data",samplingData1);
                            samplingData.put(jsonObject2);
                        }

                        jsonData.put("Consumer_connect",samplingData);
                        //samplingData2.put(jsonData);

                        jsonObject = new JSONObject();
                        jsonObject.put("MID", coverageList.get(coverageIndex).getMID());
                        jsonObject.put("Keys", "Consumer_Connect_Data");
                        jsonObject.put("JsonData", jsonData.toString());
                        jsonObject.put("UserId", coverageList.get(coverageIndex).getUserId());

                        jsonString = jsonObject.toString();
                        type = CommonString.UPLOADJsonDetail;
                    }
                    //endregion
                    break;

                case "Promotion_Data":
                    //region Coverage Data
                    db.open();

                    List<CategoryMaster> promotionData = db.getSavedPromotionInsertedChildData(coverageList.get(coverageIndex).getStoreId(), coverageList.get(coverageIndex).getVisitDate());

                    if (promotionData.size() > 0) {
                        JSONArray promotionArray = new JSONArray();
                        for (int i = 0; i < promotionData.size(); i++) {
                            jsonObject = new JSONObject();
                            jsonObject.put("MID", coverageList.get(coverageIndex).getMID());
                            jsonObject.put("User_Id", _UserId);
                            jsonObject.put(CommonString.KEY_STORE_ID, promotionData.get(i).getStore_id());
                            jsonObject.put(CommonString.KEY_PROMOTION_ID, promotionData.get(i).getId());
                            jsonObject.put(CommonString.KEY_CATEGORY_ID, promotionData.get(i).getCategoryId());
                            jsonObject.put(CommonString.KEY_IMAGE1, promotionData.get(i).getImage1());
                            jsonObject.put(CommonString.KEY_IMAGE2, promotionData.get(i).getImage2());
                            jsonObject.put(CommonString.KEY_REASON_ID, promotionData.get(i).getReasonId());
                            jsonObject.put(CommonString.KEY_PROMOTION_PRESENT, promotionData.get(i).getPresent());
                            promotionArray.put(jsonObject);
                        }

                        jsonObject = new JSONObject();
                        jsonObject.put("MID", coverageList.get(coverageIndex).getMID());
                        jsonObject.put("Keys", "Promotion_Data");
                        jsonObject.put("JsonData", promotionArray.toString());
                        jsonObject.put("UserId", coverageList.get(coverageIndex).getUserId());

                        jsonString = jsonObject.toString();
                        type = CommonString.UPLOADJsonDetail;
                    }

                    //endregion
                    break;


                case "Tester_Stock_Data":
                    db.open();
                    String value = "";
                    ArrayList<MappingTesterStock> testerStockList = db.getInsertedTestStockData(coverageList.get(coverageIndex).getStoreId().toString(), coverageList.get(coverageIndex).getVisitDate());

                    if (testerStockList.size() > 0) {
                        JSONArray compArray = new JSONArray();
                        for (int j = 0; j < testerStockList.size(); j++) {

                            JSONObject obj = new JSONObject();
                            obj.put("MID", coverageList.get(coverageIndex).getMID());
                            obj.put("UserId", _UserId);
                            // obj.put("Brand_Id", additionalVisibilityData.get(j).getCompany_cd());
                            obj.put(CommonString.KEY_STORE_ID, testerStockList.get(j).getStore_Id());
                            obj.put(CommonString.KEY_SKU_ID, testerStockList.get(j).getSkuId());
                            obj.put(CommonString.KEY_TESTER_STOCK_EXIST, testerStockList.get(j).getIsChecked());
                            compArray.put(obj);

                        }

                        jsonObject = new JSONObject();
                        jsonObject.put("MID", coverageList.get(coverageIndex).getMID());
                        jsonObject.put("Keys", "Tester_Stock_Data");
                        jsonObject.put("JsonData", compArray.toString());
                        jsonObject.put("UserId", coverageList.get(coverageIndex).getUserId());

                        jsonString = jsonObject.toString();
                        type = CommonString.UPLOADJsonDetail;
                    }
                    //endregion
                    break;


            }
            //endregion

            final int[] finalJsonIndex = {keyIndex};
            final String finalKeyName = keyList.get(keyIndex);

            if (jsonString != null && !jsonString.equalsIgnoreCase("")) {

                OkHttpClient okHttpClient = new OkHttpClient.Builder()
                        .readTimeout(20, TimeUnit.SECONDS)
                        .writeTimeout(20, TimeUnit.SECONDS)
                        .connectTimeout(20, TimeUnit.SECONDS)
                        .build();

                pd.setMessage("Uploading (" + keyIndex + "/" + keyList.size() + ") \n" + keyList.get(keyIndex) + "\n Store uploading " + (coverageIndex + 1) + "/" + coverageList.size());
                RequestBody jsonData = RequestBody.create(MediaType.parse("application/json"), jsonString);
                adapter = new Retrofit.Builder().baseUrl(CommonString.URL).client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build();
                PostApi api = adapter.create(PostApi.class);
                Call<ResponseBody> call = null;

                if (type == CommonString.COVERAGE_DETAIL) {
                    call = api.getCoverageDetail(jsonData);
                } else if (type == CommonString.UPLOADJsonDetail) {
                    call = api.getUploadJsonDetail(jsonData);
                }


                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        ResponseBody responseBody = response.body();
                        String data = null;
                        if (responseBody != null && response.isSuccessful()) {
                            try {
                                data = response.body().string();
                                if (data.equalsIgnoreCase("")) {
                                    data_global[0] = "";
                                    AlertandMessages.showAlert((Activity) context, "Invalid Data : problem occured at " + keyList.get(keyIndex), true);
                                } else {
                                    data = data.substring(1, data.length() - 1).replace("\\", "");
                                    data_global[0] = data;

                                    if (finalKeyName.equalsIgnoreCase("CoverageDetail_latest")) {
                                        try {
                                            coverageList.get(coverageIndex).setMID(Integer.parseInt(data_global[0]));
                                        } catch (NumberFormatException ex) {
                                            AlertandMessages.showAlert((Activity) context, "Error in Uploading Data at " + finalKeyName, true);
                                        }
                                    } else if (data_global[0].contains(CommonString.KEY_SUCCESS)) {

                                        if (finalKeyName.equalsIgnoreCase("GeoTag")) {
                                            db.open();
                                            db.updateInsertedGeoTagStatus(coverageList.get(coverageIndex).getStoreId(), CommonString.KEY_Y);
                                            db.updateStatus(coverageList.get(coverageIndex).getStoreId(), CommonString.KEY_Y);
                                        }
                                    } else {
                                        AlertandMessages.showAlert((Activity) context, "Error in Uploading Data at " + finalKeyName + " : " + data_global[0], true);
                                    }


                                    finalJsonIndex[0]++;
                                    if (finalJsonIndex[0] != keyList.size()) {
                                        uploadDataWithoutWait(keyList, finalJsonIndex[0], coverageList, coverageIndex);
                                    } else {
                                        pd.setMessage("updating status :" + coverageIndex);
                                        //uploading status D for current store from coverageList
                                        updateStatus(coverageList, coverageIndex, CommonString.KEY_D);
                                    }
                                }

                            } catch (Exception e) {
                                pd.dismiss();
                                AlertandMessages.showAlert((Activity) context, "Error in Uploading Data at " + finalKeyName, true);
                            }
                        } else {
                            pd.dismiss();
                            AlertandMessages.showAlert((Activity) context, "Error in Uploading Data at " + finalKeyName, true);

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        isvalid = true;
                        pd.dismiss();
                        if (t instanceof SocketTimeoutException) {
                            AlertandMessages.showAlert((Activity) context, CommonString.MESSAGE_INTERNET_NOT_AVALABLE, true);
                        } else if (t instanceof IOException) {
                            AlertandMessages.showAlert((Activity) context, CommonString.MESSAGE_INTERNET_NOT_AVALABLE, true);
                        } else if (t instanceof SocketException) {
                            AlertandMessages.showAlert((Activity) context, CommonString.MESSAGE_INTERNET_NOT_AVALABLE, true);
                        } else {
                            AlertandMessages.showAlert((Activity) context, CommonString.MESSAGE_INTERNET_NOT_AVALABLE, true);
                        }

                    }
                });

            } else {
                finalJsonIndex[0]++;
                if (finalJsonIndex[0] != keyList.size()) {
                    uploadDataWithoutWait(keyList, finalJsonIndex[0], coverageList, coverageIndex);
                } else {
                    pd.setMessage("updating status :" + coverageIndex);
                    //uploading status D for current store from coverageList
                    updateStatus(coverageList, coverageIndex, CommonString.KEY_D);

                }
            }
        } catch (Exception ex) {
            pd.dismiss();
            AlertandMessages.showAlert((Activity) context, "Error in Uploading status at coverage :" + coverageIndex, true);

        }

    }

    void updateStatus(final ArrayList<CoverageBean> coverageList, final int coverageIndex, String status) {
        if (coverageList.get(coverageIndex) != null) {
            try {
                final int[] tempcoverageIndex = {coverageIndex};
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("StoreId", coverageList.get(coverageIndex).getStoreId());
                jsonObject.put("VisitDate", coverageList.get(coverageIndex).getVisitDate());
                jsonObject.put("UserId", _UserId);
                jsonObject.put("Status", status);

                OkHttpClient okHttpClient = new OkHttpClient.Builder()
                        .readTimeout(20, TimeUnit.SECONDS)
                        .writeTimeout(20, TimeUnit.SECONDS)
                        .connectTimeout(20, TimeUnit.SECONDS)
                        .build();

                RequestBody jsonData = RequestBody.create(MediaType.parse("application/json"), jsonObject.toString());
                adapter = new Retrofit.Builder().baseUrl(CommonString.URL).client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build();
                PostApi api = adapter.create(PostApi.class);
                Call<ResponseBody> call = null;

                call = api.getCoverageStatusDetail(jsonData);

                pd.setMessage("Uploading store status " + (coverageIndex + 1) + "/" + coverageList.size());
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        ResponseBody responseBody = response.body();
                        String data = null;
                        if (responseBody != null && response.isSuccessful()) {
                            try {
                                data = response.body().string();
                                if (data.equalsIgnoreCase("")) {
                                    pd.dismiss();
                                    AlertandMessages.showAlert((Activity) context, "Error in Uploading status at coverage :" + coverageIndex, true);
                                } else {
                                    data = data.substring(1, data.length() - 1).replace("\\", "");
                                    if (data.contains("1")) {
                                        db.open();
                                        db.updateCheckoutStatus(coverageList.get(tempcoverageIndex[0]).getStoreId(), CommonString.KEY_D, CommonString.TABLE_Journey_Plan);
                                        db.updateCheckoutStatus(coverageList.get(tempcoverageIndex[0]).getStoreId(), CommonString.KEY_D, CommonString.TABLE_Journey_Plan_DBSR_Saved);
                                        tempcoverageIndex[0]++;
                                        if (tempcoverageIndex[0] != coverageList.size()) {
                                            //updateStatus(coverageList, tempcoverageIndex[0], CommonString.KEY_D);
                                            uploadDataUsingCoverageRecursive(coverageList, tempcoverageIndex[0]);
                                        } else {
                                            pd.setMessage("uploading images");
                                            String coverageDate = null;
                                            if (coverageList.size() > 0) {
                                                coverageDate = coverageList.get(0).getVisitDate();
                                            } else {
                                                coverageDate = date;
                                            }
                                            //UploadImageFileJsonList(context, coverageDate);
                                            uploadImage(coverageDate);
                                        }

                                    } else {
                                        pd.dismiss();
                                        AlertandMessages.showAlert((Activity) context, "Error in Uploading status at coverage :" + coverageIndex, true);
                                    }
                                }
                                // jsonStringList.remove(finalJsonIndex);
                                // KeyNames.remove(finalJsonIndex);
                            } catch (Exception e) {
                                pd.dismiss();
                                AlertandMessages.showAlert((Activity) context, "Error in Uploading status at coverage :" + coverageIndex, true);
                            }
                        } else {
                            pd.dismiss();
                            AlertandMessages.showAlert((Activity) context, "Error in Uploading status at coverage :" + coverageIndex, true);

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        isvalid = true;
                        pd.dismiss();
                        if (t instanceof SocketTimeoutException) {
                            AlertandMessages.showAlert((Activity) context, CommonString.MESSAGE_INTERNET_NOT_AVALABLE, true);
                        } else if (t instanceof IOException) {
                            AlertandMessages.showAlert((Activity) context, CommonString.MESSAGE_INTERNET_NOT_AVALABLE, true);
                        } else if (t instanceof SocketException) {
                            AlertandMessages.showAlert((Activity) context, CommonString.MESSAGE_INTERNET_NOT_AVALABLE, true);
                        } else {
                            AlertandMessages.showAlert((Activity) context, CommonString.MESSAGE_INTERNET_NOT_AVALABLE, true);
                        }

                    }
                });

            } catch (JSONException ex) {

            }
        }

    }

    public void uploadDataUsingCoverageRecursive(ArrayList<CoverageBean> coverageList, int coverageIndex) {
        try {
            ArrayList<String> keyList = new ArrayList<>();
            keyList.clear();
            String store_id = coverageList.get(coverageIndex).getStoreId();
            String status = null;
            pd.setMessage("Uploading store " + (coverageIndex + 1) + "/" + coverageList.size());
            db.open();

            ArrayList<JourneyPlan> journeyPlans = db.getSpecificStoreData(store_id);
            if (journeyPlans.size() > 0) {
                status = journeyPlans.get(0).getUploadStatus();
            } else {
                ArrayList<JourneyPlan> journeyPlans_DBSR = db.getSpecificStore_DBSRSavedData(store_id);
                if (journeyPlans_DBSR.size() > 0) {
                    status = journeyPlans_DBSR.get(0).getUploadStatus();
                } else {
                    status = null;
                }
            }

            if (status != null && !status.equalsIgnoreCase(CommonString.KEY_D) && !coverageList.get(coverageIndex).getReasonid().equalsIgnoreCase("11")) {
                keyList.add("CoverageDetail_latest");
                keyList.add("GeoTag");
                keyList.add("Stock_Image_Data");
                keyList.add("Stock_Data");
                keyList.add("SOS_DATA");
                keyList.add("Additional_Visibility_data");
                keyList.add("Grooming");
                keyList.add("Store_Profile");
                keyList.add("Paid_Visibility_Data");
                keyList.add("Promotion_Data");
                keyList.add("Consumer_Connect_Data");
                keyList.add("Tester_Stock_Data");

            }

            if (keyList.size() > 0) {
                UploadImageWithRetrofit upload = new UploadImageWithRetrofit(context, db, pd, from);
                upload.uploadDataWithoutWait(keyList, 0, coverageList, coverageIndex);
            } else {

                if (++coverageIndex != coverageList.size()) {
                    uploadDataUsingCoverageRecursive(coverageList, coverageIndex);
                } else {
                    String coverageDate = null;
                    if (coverageList.size() > 0) {
                        coverageDate = coverageList.get(0).getVisitDate();
                    } else {
                        coverageDate = date;
                    }
                    //UploadImageFileJsonList(context, coverageDate);
                    uploadImage(coverageDate);
                }
            }
            //endregion
        } catch (Exception e) {
            e.printStackTrace();
            pd.dismiss();
            AlertandMessages.showAlert((Activity) context, CommonString.MESSAGE_SOCKETEXCEPTION, true);
        }

    }

    void uploadImage(String coverageDate) {

        File f = new File(CommonString.FILE_PATH);
        File file[] = f.listFiles();
        if (file.length > 0) {
            uploadedFiles = 0;
            totalFiles = file.length;
            UploadImageRecursive(context,coverageDate);
        } else {
            uploadedFiles = 0;
            totalFiles = file.length;
            new StatusUpload(coverageDate).execute();
        }
    }

    //region StatusUpload
    class StatusUpload extends AsyncTask<String, String, String> {
        String coverageDate;

        StatusUpload(String coverageDate) {
            this.coverageDate = coverageDate;
        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                db = new MaricoDatabase(context);
                db.open();
                ArrayList<JourneyPlan> storeList = db.getStoreData(coverageDate);
                if (storeList.size() == 0) {
                    storeList = db.getStoreData_DBSR_Saved(coverageDate);
                }
                for (int i = 0; i < storeList.size(); i++) {
                    if (storeList.get(i).getUploadStatus().equalsIgnoreCase(CommonString.KEY_D)) {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("StoreId", storeList.get(i).getStoreId());
                        jsonObject.put("VisitDate", coverageDate);
                        jsonObject.put("UserId", _UserId);
                        jsonObject.put("Status", CommonString.KEY_U);

                        UploadImageWithRetrofit upload = new UploadImageWithRetrofit(context);
                        String jsonString2 = jsonObject.toString();
                        String result = upload.downloadDataUniversal(jsonString2, CommonString.COVERAGEStatusDetail);

                        if (result.equalsIgnoreCase(CommonString.MESSAGE_NO_RESPONSE_SERVER)) {
                            statusUpdated = false;
                            throw new SocketTimeoutException();
                        } else if (result.equalsIgnoreCase(CommonString.MESSAGE_SOCKETEXCEPTION)) {
                            statusUpdated = false;
                            throw new IOException();
                        } else if (result.equalsIgnoreCase(CommonString.MESSAGE_INVALID_JSON)) {
                            statusUpdated = false;
                            throw new JsonSyntaxException("Coverage Status Detail");
                        } else if (result.equalsIgnoreCase(CommonString.KEY_FAILURE)) {
                            statusUpdated = false;
                            throw new Exception();
                        } else {
                            statusUpdated = true;
                            if (db.updateCheckoutStatus(String.valueOf(storeList.get(i).getStoreId()), CommonString.KEY_U, CommonString.TABLE_Journey_Plan) > 0) {
                                db.deleteTableWithStoreID(String.valueOf(storeList.get(i).getStoreId()));
                                //AlertandMessages.show
                                // Alert((Activity) context, "All Image Uploaded Successfully", false);
                            }

                            if (db.updateCheckoutStatus(String.valueOf(storeList.get(i).getStoreId()), CommonString.KEY_U, CommonString.TABLE_Journey_Plan_DBSR_Saved) > 0) {
                                db.deleteTableWithStoreID(String.valueOf(storeList.get(i).getStoreId()));
                                //AlertandMessages.show
                                // Alert((Activity) context, "All Image Uploaded Successfully", false);
                            }
                        }
                    }
                }

            } catch (SocketTimeoutException e) {
                e.printStackTrace();
                AlertandMessages.showAlert((Activity) context, CommonString.MESSAGE_SOCKETEXCEPTION, true);
            } catch (IOException e) {
                e.printStackTrace();
                AlertandMessages.showAlert((Activity) context, CommonString.MESSAGE_SOCKETEXCEPTION, true);
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
                AlertandMessages.showAlert((Activity) context, CommonString.MESSAGE_INVALID_JSON, true);
            } catch (Exception e) {
                e.printStackTrace();

            }
            if (statusUpdated) {
                return CommonString.KEY_SUCCESS;
            } else {
                return CommonString.KEY_FAILURE;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pd.dismiss();
            if (s.equalsIgnoreCase(CommonString.KEY_SUCCESS)) {
                if (totalFiles == uploadedFiles && statusUpdated) {
                    AlertandMessages.showAlert((Activity) context, "All Data and Images Uploaded", true);
                } else if (totalFiles == uploadedFiles && !statusUpdated) {
                    AlertandMessages.showAlert((Activity) context, "All images uploaded Successfully, but status not updated", true);
                } else {
                    AlertandMessages.showAlert((Activity) context, "Some images not uploaded", true);
                }
            }
        }
    }
    //endregion

    //region DownloadImageTask
    class DownloadImageTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                downloadImages();
                return CommonString.KEY_SUCCESS;
            } catch (FileNotFoundException ex) {
                return CommonString.KEY_FAILURE;
            } catch (IOException ex) {
                return CommonString.KEY_FAILURE;
            }

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s.equalsIgnoreCase(CommonString.KEY_SUCCESS)) {
                pd.dismiss();
                //  AlertandMessages.showAlert((Activity) context, "All data downloaded Successfully", true);
                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context);
                builder.setCancelable(false);
                builder.setMessage("All data downloaded Successfully").setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent in = new Intent(context, StoreListActivity.class);
                                in.putExtra(CommonString.TAG_FROM, CommonString.TAG_FROM_JCP);
                                context.startActivity(in);
                                ((Activity) context).finish();
                                dialog.dismiss();
                            }

                        });

                android.support.v7.app.AlertDialog alert = builder.create();
                alert.show();

            } else {
                pd.dismiss();
                AlertandMessages.showAlert((Activity) context, "Error in downloading", true);
            }

        }

    }
    //endregion

    //region downloadImages
    void downloadImages() throws IOException {
        //Menu Image Download
        if (menuMasterGetterSetter != null) {

            List<MenuMaster> menu_master_list = menuMasterGetterSetter.getMenuMaster();

            for (int i = 0; i < menu_master_list.size(); i++) {

                String normal_img = menu_master_list.get(i).getNormalIcon();
                String path = menu_master_list.get(i).getMenuPath();

                downloadImageWithname(normal_img, path);

                String done_image = menu_master_list.get(i).getTickIcon();

                downloadImageWithname(done_image, path);

                String greyIcon = menu_master_list.get(i).getGreyIcon();

                downloadImageWithname(greyIcon, path);

            }

        }
        //endregion

    }
    //endregion

    void downloadImageWithname(String image_name, String path) throws IOException {

        if (!new File(CommonString.FILE_PATH_Downloaded
                + image_name).exists()) {
            if (image_name != null && !image_name.equalsIgnoreCase("NA")
                    && !image_name.equalsIgnoreCase("")) {
                URL url = new URL(path + image_name);
                HttpURLConnection c = (HttpURLConnection) url.openConnection();
                c.setRequestMethod("GET");
                c.getResponseCode();
                c.setConnectTimeout(20000);
                c.connect();

                if (c.getResponseCode() == 200) {
                    int length = c.getContentLength();
                    String size = new DecimalFormat("##.##")
                            .format((double) ((double) length / 1024))
                            + " KB";

                    File file = new File(CommonString.FILE_PATH_Downloaded);
                    file.mkdirs();
                    if (!size.equalsIgnoreCase("0 KB")) {

                        jj = image_name.split("\\/");
                        image_name = jj[jj.length - 1];
                        File outputFile = new File(file, image_name);
                        FileOutputStream fos = null;
                        fos = new FileOutputStream(outputFile);
                        InputStream is1 = (InputStream) c.getInputStream();
                        int bytes = 0;
                        byte[] buffer = new byte[1024];
                        int len1 = 0;

                        while ((len1 = is1.read(buffer)) != -1) {
                            bytes = (bytes + len1);
                            // data.value = (int) ((double) (((double)
                            // bytes) / length) * 100);
                            fos.write(buffer, 0, len1);
                        }

                        fos.close();
                        is1.close();

                    }
                } else {
                    c.disconnect();
                }
            }
        }

    }


    String createTable(TableStructureGetterSetter tableGetSet) {
        List<TableStructure> tableList = tableGetSet.getTableStructure();
        for (int i = 0; i < tableList.size(); i++) {
            String table = tableList.get(i).getSqlText();
            if (db.createtable(table) == 0) {
                return table;
            }
        }
        return CommonString.KEY_SUCCESS;
    }

    //region downloadDataUniversalWithoutWait
    public void downloadDataUniversalWithoutWait(final ArrayList<String> jsonStringList, final ArrayList<String> KeyNames, int downloadindex, int type) {
        status = 0;
        isvalid = false;
        final String[] data_global = {""};
        String jsonString = "", KeyName = "";
        int jsonIndex = 0;

        if (jsonStringList.size() > 0) {

            jsonString = jsonStringList.get(downloadindex);
            KeyName = KeyNames.get(downloadindex);
            jsonIndex = downloadindex;

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(20, TimeUnit.SECONDS)
                    .writeTimeout(20, TimeUnit.SECONDS)
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .build();

            pd.setMessage("Downloading (" + downloadindex + "/" + listSize + ") \n" + KeyName + "");
            RequestBody jsonData = RequestBody.create(MediaType.parse("application/json"), jsonString);
            adapter = new Retrofit.Builder().baseUrl(CommonString.URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            PostApi api = adapter.create(PostApi.class);
            Call<ResponseBody> call = null;

            if (type == CommonString.LOGIN_SERVICE) {
                call = api.getLogindetail(jsonData);
            } else if (type == CommonString.DOWNLOAD_ALL_SERVICE) {
                call = api.getDownloadAll(jsonData);
            } else if (type == CommonString.COVERAGE_DETAIL) {
                call = api.getCoverageDetail(jsonData);
            } else if (type == CommonString.UPLOADJCPDetail) {
                call = api.getUploadJCPDetail(jsonData);
            } else if (type == CommonString.UPLOADJsonDetail) {
                call = api.getUploadJsonDetail(jsonData);
            } else if (type == CommonString.COVERAGEStatusDetail) {
                call = api.getCoverageStatusDetail(jsonData);
            } else if (type == CommonString.CHECKOUTDetail) {
                call = api.getCheckout(jsonData);
            } else if (type == CommonString.DELETE_COVERAGE) {
                call = api.deleteCoverageData(jsonData);
            }

            final int[] finalJsonIndex = {jsonIndex};
            final String finalKeyName = KeyName;
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    ResponseBody responseBody = response.body();
                    String data = null;
                    if (responseBody != null && response.isSuccessful()) {
                        try {
                            data = response.body().string();
                            if (data.equalsIgnoreCase("")) {
                                data_global[0] = "";
                            } else {
                                data = data.substring(1, data.length() - 1).replace("\\", "");
                                data_global[0] = data;
                                if (finalKeyName.equalsIgnoreCase("Table_Structure")) {
                                    editor.putInt(CommonString.KEY_DOWNLOAD_INDEX, finalJsonIndex[0]);
                                    editor.apply();
                                    tableStructureObj = new Gson().fromJson(data, TableStructureGetterSetter.class);
                                    String isAllTableCreated = createTable(tableStructureObj);
                                    if (isAllTableCreated != CommonString.KEY_SUCCESS) {
                                        pd.dismiss();
                                        AlertandMessages.showAlert((Activity) context, isAllTableCreated + " not created", true);
                                    }
                                } else {
                                    editor.putInt(CommonString.KEY_DOWNLOAD_INDEX, finalJsonIndex[0]);
                                    editor.apply();
                                    //region Description
                                    switch (finalKeyName) {
                                        case "Journey_Plan":
                                            if (!data.contains("No Data")) {
                                                jcpObject = new Gson().fromJson(data, JCPGetterSetter.class);
                                                if (jcpObject != null && !db.insertJCPData(jcpObject)) {
                                                    pd.dismiss();
                                                    AlertandMessages.showSnackbarMsg(context, "JCP data data not saved");
                                                }
                                            } else {
                                                throw new java.lang.Exception();
                                            }
                                            break;
                                        case "Menu_Master":
                                            if (!data.contains("No Data")) {
                                                menuMasterGetterSetter = new Gson().fromJson(data, MenuMasterGetterSetter.class);
                                                if (menuMasterGetterSetter != null && !db.insertMenuMasterData(menuMasterGetterSetter)) {
                                                    pd.dismiss();
                                                    AlertandMessages.showSnackbarMsg(context, "Menu Master data not saved");
                                                }
                                            } else {
                                                if (rightname != null && !rightname.equalsIgnoreCase("DBSR")) {
                                                    // throw new java.lang.Exception();
                                                }
                                            }
                                            break;
                                        case "Mapping_Menu":
                                            if (!data.contains("No Data")) {
                                                mappingMenuGetterSetter = new Gson().fromJson(data, MappingMenuGetterSetter.class);
                                                if (mappingMenuGetterSetter != null && !db.insertMappingMenuData(mappingMenuGetterSetter)) {
                                                    pd.dismiss();
                                                    AlertandMessages.showSnackbarMsg(context, "Mapping Menu data not saved");
                                                }
                                            } else {
                                                throw new java.lang.Exception();
                                            }
                                            break;


                                        case "Window_Checklist":
                                            if (!data.contains("No Data")) {
                                                windowChecklist = new Gson().fromJson(data, WindowChecklistGetterSetter.class);
                                                if (windowChecklist != null && !db.insertWindowChecklistData(windowChecklist)) {
                                                    pd.dismiss();
                                                    AlertandMessages.showSnackbarMsg(context, "Window Checklist data not saved");
                                                }
                                            } else {
                                                throw new java.lang.Exception();
                                            }
                                            break;
                                        case "Window_Checklist_Answer":
                                            if (!data.contains("No Data")) {
                                                windcheckanser = new Gson().fromJson(data, WindowCheckAnswerGetterSetter.class);
                                                if (windcheckanser != null && !db.insertwindowChecklistAnsdata(windcheckanser)) {
                                                    pd.dismiss();
                                                    AlertandMessages.showSnackbarMsg(context, "Window check Answer data not saved");
                                                }
                                            } else {
                                                throw new java.lang.Exception();
                                            }
                                            break;


                                        case "Non_Working_Reason":
                                            if (!data.contains("No Data")) {
                                                nonWorkingObj = new Gson().fromJson(data, NonWorkingReasonGetterSetter.class);
                                                if (nonWorkingObj != null && !db.insertNonWorkingData(nonWorkingObj)) {
                                                    pd.dismiss();
                                                    AlertandMessages.showSnackbarMsg(context, "Non Working Reason data not saved");
                                                }
                                            } else {
                                                throw new java.lang.Exception();
                                            }
                                            break;
                                        //case "Mapping_Visibility_Initiatives":
                                        case "Mapping_Visibility_Initiatives_New":
                                            if (!data.contains("No Data")) {
                                                mappingInitiative = new Gson().fromJson(data, MappingInitiativeGetterSetter.class);
                                                if (mappingInitiative != null && !db.insertmappingInitiativedata(mappingInitiative)) {
                                                    pd.dismiss();
                                                    AlertandMessages.showSnackbarMsg(context, "Mapping Initiative data not saved");
                                                }
                                            } else {
                                                if (rightname != null && !rightname.equalsIgnoreCase("DBSR")) {
                                                    //  throw new java.lang.Exception();
                                                }
                                            }
                                            break;

                                        case "Non_Window_Reason":
                                            if (!data.contains("No Data")) {
                                                nonWindowReasonGetterSetter = new Gson().fromJson(data, NonWindowReasonGetterSetter.class);
                                                if (nonWindowReasonGetterSetter != null && !db.insertNonWindowReasonData(nonWindowReasonGetterSetter)) {
                                                    pd.dismiss();
                                                    AlertandMessages.showSnackbarMsg(context, "Non Window data not saved");
                                                }
                                            } else {
                                                throw new java.lang.Exception();
                                            }
                                            break;
                                        case "Category_Master":
                                            if (!data.contains("No Data")) {
                                                categoryMasterObject = new Gson().fromJson(data, CategoryMasterGetterSetter.class);
                                                if (categoryMasterObject != null && !db.insertCategoryMasterData(categoryMasterObject)) {
                                                    pd.dismiss();
                                                    AlertandMessages.showSnackbarMsg(context, "Category Master data not saved");
                                                }
                                            } else {
                                                throw new java.lang.Exception();
                                            }
                                            break;
                                        case "Mapping_Category_Checklist":
                                            if (!data.contains("No Data")) {
                                                mappingCategoryChecklistGetterSetter = new Gson().fromJson(data, MappingCategoryChecklistGetterSetter.class);
                                                if (mappingCategoryChecklistGetterSetter != null && !db.insertMappingCategoryChecklistdata(mappingCategoryChecklistGetterSetter)) {
                                                    pd.dismiss();
                                                    AlertandMessages.showSnackbarMsg(context, "Mapping Category Checklist data not saved");
                                                }
                                            } else {
                                                if (rightname != null && !rightname.equalsIgnoreCase("DBSR")) {
                                                    //  throw new java.lang.Exception();
                                                }
                                            }
                                            break;
                                        case "Non_Category_Reason":
                                            if (!data.contains("No Data")) {
                                                nonCategoryReasonGetterSetter = new Gson().fromJson(data, NonCategoryReasonGetterSetter.class);
                                                if (nonCategoryReasonGetterSetter != null && !db.insertNonCategoryReasonData(nonCategoryReasonGetterSetter)) {
                                                    pd.dismiss();
                                                    AlertandMessages.showSnackbarMsg(context, "Non Category Reason not saved");
                                                }
                                            } else {
                                                throw new java.lang.Exception();
                                            }
                                            break;
                                        case "Brand_Master":
                                            if (!data.contains("No Data")) {
                                                brandMasterObject = new Gson().fromJson(data, BrandMasterGetterSetter.class);
                                                if (brandMasterObject != null && !db.insertBrandMasterData(brandMasterObject)) {
                                                    pd.dismiss();
                                                    AlertandMessages.showSnackbarMsg(context, "Brand Master data not saved");
                                                }
                                            } else {
                                                throw new java.lang.Exception();
                                            }
                                            break;
                                        case "Store_Type_Master":
                                            if (!data.contains("No Data")) {
                                                storeTypeMasterGetterSetter = new Gson().fromJson(data, StoreTypeMasterGetterSetter.class);
                                                if (storeTypeMasterGetterSetter != null && !db.insertStoreTypeMasterData(storeTypeMasterGetterSetter)) {
                                                    pd.dismiss();
                                                    AlertandMessages.showSnackbarMsg(context, "Store Type Master data not saved");
                                                }
                                            } else {
                                                throw new java.lang.Exception();
                                            }
                                            break;

                                        case "Mapping_Posm":
                                            if (!data.contains("No Data")) {
                                                mappingPosmGetterSetter = new Gson().fromJson(data, MappingPosmGetterSetter.class);
                                                if (mappingPosmGetterSetter != null && !db.insertMappingPosmData(mappingPosmGetterSetter)) {
                                                    pd.dismiss();
                                                    AlertandMessages.showSnackbarMsg(context, "Mapping Posm data not saved");
                                                }
                                            } else {
                                                //throw new java.lang.Exception();
                                            }
                                            break;

                                        case "Posm_Master":
                                            if (!data.contains("No Data")) {
                                                posmMasterGetterSetter = new Gson().fromJson(data, PosmMasterGetterSetter.class);
                                                if (posmMasterGetterSetter != null && !db.insertPosmMasterData(posmMasterGetterSetter)) {
                                                    pd.dismiss();
                                                    AlertandMessages.showSnackbarMsg(context, "Posm Master data not saved");
                                                }
                                            } else {
                                                throw new java.lang.Exception();
                                            }
                                            break;

                                        case "Mapping_Visicooler":
                                            if (!data.contains("No Data")) {
                                                mappingVisicoolerGetterSetter = new Gson().fromJson(data, MappingVisicoolerGetterSetter.class);
                                                if (mappingVisicoolerGetterSetter != null && !db.insertMapppingVisicoolerData(mappingVisicoolerGetterSetter)) {
                                                    pd.dismiss();
                                                    AlertandMessages.showSnackbarMsg(context, "Mapping Visicooler data not saved");
                                                }
                                            } else {
                                                //throw new java.lang.Exception();
                                            }
                                            break;


                                        //case "Non_Posm_Reason":
                                        case "Non_Execution_Reason":
                                            if (!data.contains("No Data")) {
                                                nonExecutionReasonGetterSetter = new Gson().fromJson(data, NonExecutionReasonGetterSetter.class);
                                                if (nonExecutionReasonGetterSetter != null && !db.insertNonPosmReseonData(nonExecutionReasonGetterSetter)) {
                                                    pd.dismiss();
                                                    AlertandMessages.showSnackbarMsg(context, "Non_Execution_Reason data not saved");
                                                }
                                            } else {
                                                throw new java.lang.Exception();
                                            }
                                            break;

                                        case "Mapping_Stock":
                                            if (!data.contains("No Data")) {
                                                mappingFocusSkuGetterSetter = new Gson().fromJson(data, MappingFocusSkuGetterSetter.class);
                                                if (mappingFocusSkuGetterSetter != null && !db.insertMappingFocusSkuData(mappingFocusSkuGetterSetter)) {
                                                    pd.dismiss();
                                                    AlertandMessages.showSnackbarMsg(context, "Mapping Stock data not saved");
                                                }
                                            } else {
                                              //  throw new java.lang.Exception();
                                            }
                                            break;


                                        case "Sku_Master":
                                            if (!data.contains("No Data")) {
                                                skuMasterGetterSetter = new Gson().fromJson(data, SkuMasterGetterSetter.class);
                                                if (skuMasterGetterSetter != null && !db.insertSkuMasterData(skuMasterGetterSetter)) {
                                                    pd.dismiss();
                                                    AlertandMessages.showSnackbarMsg(context, "Sku Master data not saved");
                                                }
                                            } else {
                                                throw new java.lang.Exception();
                                            }
                                            break;


                                        case "Display_Master":
                                            if (!data.contains("No Data")) {
                                                displayMasterGetterSetter = new Gson().fromJson(data, DisplayMasterGetterSetter.class);
                                                if (displayMasterGetterSetter != null && !db.insertDisplayMasterData(displayMasterGetterSetter)) {
                                                    pd.dismiss();
                                                    AlertandMessages.showSnackbarMsg(context, "Display Master data not saved");
                                                }
                                            } else {
                                                throw new java.lang.Exception();
                                            }
                                            break;


                                        case "Sub_Category_Master":
                                            if (!data.contains("No Data")) {
                                                subCategoryMasterGetterSetter = new Gson().fromJson(data, SubCategoryMasterGetterSetter.class);
                                                if (subCategoryMasterGetterSetter != null && !db.insertSubCategoryMaster(subCategoryMasterGetterSetter)) {
                                                    pd.dismiss();
                                                    AlertandMessages.showSnackbarMsg(context, "Sub_Category_Master data not saved");
                                                }
                                            } else {
                                                throw new java.lang.Exception();
                                            }
                                            break;

                                        case "Mapping_Sampling":
                                            if (!data.contains("No Data")) {
                                                mappingSamplingGetterSetter = new Gson().fromJson(data, MappingSamplingGetterSetter.class);
                                                if (mappingSamplingGetterSetter != null && !db.insertMappingSampling(mappingSamplingGetterSetter)) {
                                                    pd.dismiss();
                                                    AlertandMessages.showSnackbarMsg(context, "Mapping Sampling data not saved");
                                                }
                                            } else {
                                              //  throw new java.lang.Exception();
                                            }
                                            break;

                                        case "mapping_Share_Of_Shelf":
                                            if (!data.contains("No Data")) {
                                                mappingShareOfShelfGetterSetter = new Gson().fromJson(data, MappingShareOfShelfGetterSetter.class);
                                                if (mappingShareOfShelfGetterSetter != null && !db.insertShareOfShelfMaster(mappingShareOfShelfGetterSetter)) {
                                                    pd.dismiss();
                                                    AlertandMessages.showSnackbarMsg(context, "Mapping Sampling data not saved");
                                                }
                                            } else {
                                              //  throw new java.lang.Exception();
                                            }
                                            break;
                                        case "Mapping_Paid_Visibility":
                                            if (!data.contains("No Data")) {
                                                mappingPaidVisibilityGetterSetter = new Gson().fromJson(data, MappingPaidVisibilityGetterSetter.class);
                                                if (mappingPaidVisibilityGetterSetter != null && !db.insertMappingPaidVisibility(mappingPaidVisibilityGetterSetter)) {
                                                    pd.dismiss();
                                                    AlertandMessages.showSnackbarMsg(context, "Mapping Paid Visibility data not saved");
                                                }
                                            } else {
                                               // throw new java.lang.Exception();
                                            }
                                            break;
                                        case "Mapping_Promotion":
                                            if (!data.contains("No Data")) {
                                                mappingPromotionGetterSetter = new Gson().fromJson(data, MappingPromotionGetterSetter.class);
                                                if (mappingPromotionGetterSetter != null && !db.insertMappingPromotionData(mappingPromotionGetterSetter)) {
                                                    pd.dismiss();
                                                    AlertandMessages.showSnackbarMsg(context, "Mapping_Promotion data not saved");
                                                }
                                            } else {
                                               // throw new java.lang.Exception();
                                            }
                                            break;
                                        case "Feedback_Master":
                                            if (!data.contains("No Data")) {
                                                feedbackMasterGetterSetter = new Gson().fromJson(data, FeedbackMasterGetterSetter.class);
                                                if (feedbackMasterGetterSetter != null && !db.insertFeedbackData(feedbackMasterGetterSetter)) {
                                                    pd.dismiss();
                                                    AlertandMessages.showSnackbarMsg(context, "Feedback Master data not saved");
                                                }
                                            } else {
                                                throw new java.lang.Exception();
                                            }
                                            break;

                                        case "Sampling_Checklist":
                                            if (!data.contains("No Data")) {
                                                samplingChecklistGetterSetter = new Gson().fromJson(data, SamplingChecklistGetterSetter.class);
                                                if (samplingChecklistGetterSetter != null && !db.insertSamplingCheckListData(samplingChecklistGetterSetter)) {
                                                    pd.dismiss();
                                                    AlertandMessages.showSnackbarMsg(context, "Sampling CheckList data not saved");
                                                }
                                            } else {
                                                throw new java.lang.Exception();
                                            }
                                            break;


                                        //case "Mapping_Visibility_Initiatives":
                                        case "Performance":
                                            if (!data.contains("No Data")) {
                                                performancePageObj = new Gson().fromJson(data, PerformancePageGetterSetter.class);
                                                if (performancePageObj != null && !db.insertPerformancedata(performancePageObj)) {
                                                    pd.dismiss();
                                                    AlertandMessages.showSnackbarMsg(context, "Performance data not saved");
                                                }
                                            }
                                            break;

                                        case "Mapping_Tester_Stock":
                                            if (!data.contains("No Data")) {
                                                mappingTesterStockGetterSetter = new Gson().fromJson(data, MappingTesterStockGetterSetter.class);
                                                if (mappingTesterStockGetterSetter != null && !db.insertMappingTesterData(mappingTesterStockGetterSetter)) {
                                                    pd.dismiss();
                                                    AlertandMessages.showSnackbarMsg(context, "Mapping Tester data not saved");
                                                }
                                            } else {
                                                //  throw new java.lang.Exception();
                                            }
                                            break;


                                        case "Promoter_Target":
                                            if (!data.contains("No Data")) {
                                                promoterTargetObj = new Gson().fromJson(data, PromoterTargetGetterSetter.class);
                                                if (promoterTargetObj != null && !db.insertPromoterdata(promoterTargetObj)) {
                                                    pd.dismiss();
                                                    AlertandMessages.showSnackbarMsg(context, "Promoter data not saved");
                                                }
                                            }
                                            break;

                                        case "Promoter_Skuwise_Sale":
                                            if (!data.contains("No Data")) {
                                                promoterSkuwiseSaleGetterSetter = new Gson().fromJson(data, PromoterSkuwiseSaleGetterSetter.class);
                                                if (promoterSkuwiseSaleGetterSetter != null && !db.insertPromoterTargetData(promoterSkuwiseSaleGetterSetter)) {
                                                    pd.dismiss();
                                                    AlertandMessages.showSnackbarMsg(context, "Promoter Target data not saved");
                                                }
                                            }
                                            break;

                                        case "Promoter_TDP_SaleTarget":
                                            if (!data.contains("No Data")) {
                                                promoterTDPSaleTargetGetterSetter = new Gson().fromJson(data, PromoterTDPSaleTargetGetterSetter.class);
                                                if (promoterTDPSaleTargetGetterSetter != null && !db.insertTDPSaleTargetData(promoterTDPSaleTargetGetterSetter)) {
                                                    pd.dismiss();
                                                    AlertandMessages.showSnackbarMsg(context, "TDP Sale Type data not saved");
                                                }
                                            }
                                            break;


                                    }
                                }
                            }
                            // jsonStringList.remove(finalJsonIndex);
                            // KeyNames.remove(finalJsonIndex);
                            finalJsonIndex[0]++;
                            if (finalJsonIndex[0] != KeyNames.size()) {
                                editor.putInt(CommonString.KEY_DOWNLOAD_INDEX, finalJsonIndex[0]);
                                editor.apply();
                                downloadDataUniversalWithoutWait(jsonStringList, KeyNames, finalJsonIndex[0], CommonString.DOWNLOAD_ALL_SERVICE);
                            } else {
                                editor.putInt(CommonString.KEY_DOWNLOAD_INDEX, 0);
                                editor.apply();
                                //pd.dismiss();
                                //AlertandMessages.showAlert((Activity) context, "All data downloaded Successfully", true);
                               /* android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context);
                                builder.setCancelable(false);
                                builder.setMessage("All data downloaded Successfully").setCancelable(false)
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.dismiss();
                                            }

                                        });

                                android.support.v7.app.AlertDialog alert = builder.create();
                                alert.show();*/
                                //downloadImages();
                                pd.setMessage("Downloading Images");
                                new DownloadImageTask().execute();
                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                            editor.putInt(CommonString.KEY_DOWNLOAD_INDEX, finalJsonIndex[0]);
                            editor.apply();
                            pd.dismiss();
                            AlertandMessages.showAlert((Activity) context, "Error in downloading Data at " + finalKeyName, true);
                        }
                    } else {
                        editor.putInt(CommonString.KEY_DOWNLOAD_INDEX, finalJsonIndex[0]);
                        editor.apply();
                        pd.dismiss();
                        AlertandMessages.showAlert((Activity) context, "Error in downloading Data at " + finalKeyName, true);

                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    isvalid = true;
                    pd.dismiss();
                    if (t instanceof SocketTimeoutException) {
                        AlertandMessages.showAlert((Activity) context, CommonString.MESSAGE_INTERNET_NOT_AVALABLE, true);
                    } else if (t instanceof IOException) {
                        AlertandMessages.showAlert((Activity) context, CommonString.MESSAGE_INTERNET_NOT_AVALABLE, true);
                    } else if (t instanceof SocketException) {
                        AlertandMessages.showAlert((Activity) context, CommonString.MESSAGE_INTERNET_NOT_AVALABLE, true);
                    } else {
                        AlertandMessages.showAlert((Activity) context, CommonString.MESSAGE_INTERNET_NOT_AVALABLE, true);
                    }

                }
            });

        } else {
            editor.putInt(CommonString.KEY_DOWNLOAD_INDEX, 0);
            editor.apply();
            // pd.dismiss();
            // AlertandMessages.showAlert((Activity) context, "All data downloaded Successfully", true);
            //pd.setMessage("Downloading Images");
            //new DownloadImageTask().execute();
        }

    }
    //endregion

}
