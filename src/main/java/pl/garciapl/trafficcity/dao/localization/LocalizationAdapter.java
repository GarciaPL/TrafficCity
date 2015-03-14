/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.garciapl.trafficcity.dao.localization;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * @author lciesluk
 */
public class LocalizationAdapter implements JsonDeserializer<Tolerance> {

    @Override
    public Tolerance deserialize(JsonElement json, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        return Tolerance.valueOf(json.getAsString().toUpperCase());
    }

}
