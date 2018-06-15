package co.example.network;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Crish on 09.12.2017.
 */

public interface Api {
    @GET("service/end_point")
    Observable<String> getHistoricalPlaces(@Query("lang") int lang);

    @GET("service/end_point")
    Observable<String> getAboutUzbekistan(@Query("lang") int lang);

    @GET("service/end_point")
    Observable<String> getCategories(@Query("lang") int lang, @Query("parent") long parent);

    @GET("service/end_point")
    Observable<String> getExchangeRates();

    @GET("service/end_point")
    Observable<String> getDiscounts(@Query("lang") int lang);

    @GET("service/end_point")
    Observable<String> getWeather(@Query("lang") int lang);

    @GET("service/end_point")
    Observable<String> signUp(@Query("lang") int lang, @Query("username") String userName, @Query("email") String email, @Query("name") String name, @Query("phone") String phone, @Query("address") String address, @Query("password") String password);

    @GET("service/end_point")
    Observable<String> signIn(@Query("lang") int lang, @Query("username") String userName, @Query("password") String password);

    @GET("service/end_point")
    Observable<String> restorePassword(@Query("lang") int lang, @Query("email") String email);

    @GET("service/end_point")
    Observable<String> getAboutService(@Query("lang") int lang);

    @GET("service/end_point")
    Observable<String> getPublicOffer(@Query("lang") int lang);

    @GET("service/end_point")
    Observable<String> getHowToUse(@Query("lang") int lang);

    @GET("service/end_point")
    Observable<String> getInsurance(@Query("lang") int lang);

    @POST("service/end_point")
    @FormUrlEncoded
    Observable<String> activateDiscountCard(@Query("lang") int lang, @Field("serial") String serialNumber, @Field("secret") String activationCode, @Field("device_id") String deviceId);

    @GET("service/end_point")
    Observable<String> getTouristInformationCard(@Query("lang") int lang);

    @GET("service/end_point")
    Observable<String> getCompanies(@Query("lang") int lang, @Query("search_text") String searchText, @Query("category_id") Long categoryId, @Query("region_id") Long regionId, @Query("district_id") Long districtId, @Query("page") int page, @Query("lat") double latitude, @Query("lon") double longitude);

    @GET("service/end_point")
    Observable<String> getCompanyDescription(@Query("lang") int lang, @Query("sale_id") long saleId);

    @GET("service/end_point")
    Observable<String> getSearch(@Query("lang") int lang);

    @GET("service/end_point")
    Observable<String> getRegionDescription(@Query("lang") int lang, @Query("id") long id);

    @GET("service/end_point")
    Observable<String> getSightDescription(@Query("lang") int lang, @Query("id") long id);

    @GET("service/end_point")
    Observable<String> getLanguages();

    @GET("service/end_point")
    Observable<String> getFavorites(@Query("lang") int lang, @Query("user_id") long userId);

    @POST("service/end_point")
    @FormUrlEncoded
    Observable<String> addFavorite(@Field("user_id") long userId, @Field("sale_id") long saleId);

    @POST("service/end_point")
    @FormUrlEncoded
    Observable<String> removeFavorite(@Field("user_id") long userId, @Field("sale_id") long saleId);

    @GET("https://maps.googleapis.com/maps/api/directions/json")
    Observable<String> getDirections(@Query("origin") String origin, @Query("destination") String destination, @Query("key") String key);
}
