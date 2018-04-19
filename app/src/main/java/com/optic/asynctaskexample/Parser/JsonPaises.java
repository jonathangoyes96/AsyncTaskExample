package com.optic.asynctaskexample.Parser;

import com.optic.asynctaskexample.Models.Country;
import com.optic.asynctaskexample.Models.Result;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 19/04/2018.
 */

public class JsonPaises {

    public static List<Result> getDataJson(String data) throws JSONException {

        JSONObject jsonData = new JSONObject(data);
        JSONObject itemData = jsonData.getJSONObject("RestResponse");



        JSONArray jsonArray = itemData.getJSONArray("result");

        List<Result> resultList = new ArrayList<>();


        for(int i = 0; i < jsonArray.length(); i++) {

            JSONObject item = jsonArray.getJSONObject(i);

            Result result = new Result(item.getString("name"), item.getString("alpha2_code"), item.getString("alpha3_code"));


            resultList.add(result);

        }

        return resultList;
    }

}
