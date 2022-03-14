package com.coolweather.android.util;

import android.text.TextUtils;

import com.coolweather.android.db.City;
import com.coolweather.android.db.County;
import com.coolweather.android.db.Province;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Utility {
    public static boolean handleProvinceResponse(String response){
        if (!TextUtils.isEmpty(response)){
//            Gson gson = new Gson();
//            List<Province> provinceList = gson.fromJson(response,new TypeToken<List<Province>>(){}.getType());
            try{
            JSONArray allProvinces = new JSONArray(response);
            for (int i = 0;i < allProvinces.length();i++){
                JSONObject provinceObject = allProvinces.getJSONObject(i);
                Province province = new Province();
                province.setProvinceName(provinceObject.getString("name"));
                province.setProvinceCode(provinceObject.getInt("id"));
                province.save();
            }
            return true;
        } catch (JSONException e){
                e.printStackTrace();
            }
        }
        return  false;
    }


    public static boolean handleCityResponse(String response ,int provinceId){
        if (!TextUtils.isEmpty(response)){
//            Gson gson = new Gson();
//            List<City> cityList = gson.fromJson(response,new TypeToken<List<City>>(){}.getType());
            try{
                JSONArray allCities = new JSONArray(response);
                for (int i = 0;i < allCities.length();i++){
                    JSONObject cityObject = allCities.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            } catch (JSONException e){
                e.printStackTrace();
            }
        }
        return  false;
    }


    public static boolean handleCountResponse(String response ,int cityId){
        if (!TextUtils.isEmpty(response)){
//            Gson gson = new Gson();
//            List<County> cityList = gson.fromJson(response,new TypeToken<List<County>>(){}.getType());
            try{
                JSONArray allCounties = new JSONArray(response);
                for (int i = 0;i < allCounties.length();i++){
                    JSONObject countyObject = allCounties.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(countyObject.getString("name"));
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            } catch (JSONException e){
                e.printStackTrace();
            }
        }
        return  false;
    }

}
