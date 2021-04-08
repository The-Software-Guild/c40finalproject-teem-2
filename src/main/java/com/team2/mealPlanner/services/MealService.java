package com.team2.mealPlanner.services;


import com.team2.mealPlanner.utils.ApiMeal;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Logger;

@Service
public class MealService {
    static Logger logger = Logger.getLogger(Controller.class.getName());


    private static String meal_API_URL= "https://www.themealdb.com/api/json/v1/1/search.php?s=";

    @Autowired
    private RestTemplate restTemplate;


    public Optional<ApiMeal> getMealById(int id)
    {
        List<ApiMeal> apiMealList = getMealsFromApi();
        return apiMealList.stream()
                    .filter(p->p.getIdMeal().equals(""+id))
                    .findFirst()
                    ;

    }

    public List<ApiMeal> getMealsFromApi() {

        List<ApiMeal> apiMealList = new LinkedList<>();
        String inline = "";
        try
        {
            URL url = new URL(meal_API_URL);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responsecode = conn.getResponseCode();
            System.out.println("Response code is: " +responsecode);

            if(responsecode != 200)
                throw new RuntimeException("HttpResponseCode: " +responsecode);
            else
            {
                //Scanner functionality will read the JSON data from the stream
                Scanner sc = new Scanner(url.openStream());
                while(sc.hasNext())
                {
                    inline+=sc.nextLine();
                }
                System.out.println("\nJSON Response in String format");
                System.out.println(inline);
                sc.close();
            }

            JSONParser parse = new JSONParser();
            JSONObject jobj = (JSONObject)parse.parse(inline);
            JSONArray jsonarr_1 = (JSONArray) jobj.get("meals");
            for(int i=0;i<jsonarr_1.size();i++)
            {
                ApiMeal apiMeal = new ApiMeal();

                JSONObject jsonObject = (JSONObject)jsonarr_1.get(i);
                apiMeal.setIdMeal(jsonObject.get("idMeal").toString());
                apiMeal.setStrMeal(jsonObject.get("strMeal").toString());

                if(jsonObject.get("strDrinkAlternate") == null) apiMeal.setStrDrinkAlternate("");
                else apiMeal.setStrDrinkAlternate(jsonObject.get("strDrinkAlternate").toString());

                apiMeal.setStrCategory(jsonObject.get("strCategory").toString());
                apiMeal.setStrArea(jsonObject.get("strArea").toString());
                apiMeal.setStrInstructions(jsonObject.get("strInstructions").toString());
                apiMeal.setStrMealThumb(jsonObject.get("strMealThumb").toString());

                if(jsonObject.get("strTags") == null) apiMeal.setStrTags("");
                else apiMeal.setStrTags(jsonObject.get("strTags").toString());

                apiMeal.setStrYoutube(jsonObject.get("strYoutube").toString());
                apiMeal.setStrIngredient1(jsonObject.get("strIngredient1").toString());
                apiMeal.setStrIngredient2(jsonObject.get("strIngredient2").toString());
                apiMeal.setStrIngredient3(jsonObject.get("strIngredient3").toString());
                apiMeal.setStrIngredient4(jsonObject.get("strIngredient4").toString());
                apiMeal.setStrIngredient5(jsonObject.get("strIngredient5").toString());

                if(jsonObject.get("strIngredient6") == null) apiMeal.setStrIngredient6("");
                else apiMeal.setStrIngredient6(jsonObject.get("strIngredient6").toString());

                apiMeal.setStrIngredient7(jsonObject.get("strIngredient7").toString());
                apiMeal.setStrIngredient8(jsonObject.get("strIngredient8").toString());
                apiMeal.setStrIngredient9(jsonObject.get("strIngredient9").toString());
                apiMeal.setStrIngredient10(jsonObject.get("strIngredient10").toString());
                apiMeal.setStrIngredient11(jsonObject.get("strIngredient11").toString());
                apiMeal.setStrIngredient12(jsonObject.get("strIngredient12").toString());
                apiMeal.setStrIngredient13(jsonObject.get("strIngredient13").toString());
                apiMeal.setStrIngredient14(jsonObject.get("strIngredient14").toString());
                apiMeal.setStrIngredient15(jsonObject.get("strIngredient15").toString());

                if(jsonObject.get("strIngredient16") == null) apiMeal.setStrIngredient16("");
                else apiMeal.setStrIngredient16(jsonObject.get("strIngredient16").toString());

                if(jsonObject.get("strIngredient17") == null) apiMeal.setStrIngredient17("");
                else apiMeal.setStrIngredient17(jsonObject.get("strIngredient17").toString());

                if(jsonObject.get("strIngredient18") == null) apiMeal.setStrIngredient18("");
                else apiMeal.setStrIngredient18(jsonObject.get("strIngredient18").toString());

                if(jsonObject.get("strIngredient19") == null) apiMeal.setStrIngredient19("");
                else apiMeal.setStrIngredient19(jsonObject.get("strIngredient19").toString());

                if(jsonObject.get("strIngredient20") == null) apiMeal.setStrIngredient20("");
                else apiMeal.setStrIngredient20(jsonObject.get("strIngredient20").toString());


                apiMeal.setStrMeasure1(jsonObject.get("strMeasure1").toString());
                apiMeal.setStrMeasure2(jsonObject.get("strMeasure2").toString());
                apiMeal.setStrMeasure3(jsonObject.get("strMeasure3").toString());
                apiMeal.setStrMeasure4(jsonObject.get("strMeasure4").toString());
                apiMeal.setStrMeasure5(jsonObject.get("strMeasure5").toString());
                apiMeal.setStrMeasure6(jsonObject.get("strMeasure6").toString());
                apiMeal.setStrMeasure7(jsonObject.get("strMeasure7").toString());
                apiMeal.setStrMeasure8(jsonObject.get("strMeasure8").toString());
                apiMeal.setStrMeasure9(jsonObject.get("strMeasure9").toString());
                apiMeal.setStrMeasure10(jsonObject.get("strMeasure10").toString());
                apiMeal.setStrMeasure11(jsonObject.get("strMeasure11").toString());
                apiMeal.setStrMeasure12(jsonObject.get("strMeasure12").toString());
                apiMeal.setStrMeasure13(jsonObject.get("strMeasure13").toString());
                apiMeal.setStrMeasure14(jsonObject.get("strMeasure14").toString());
                apiMeal.setStrMeasure15(jsonObject.get("strMeasure15").toString());

                if(jsonObject.get("strMeasure16") == null) apiMeal.setStrMeasure16("");
                else apiMeal.setStrMeasure16(jsonObject.get("strMeasure16").toString());

                if(jsonObject.get("strMeasure17") == null) apiMeal.setStrMeasure17("");
                else apiMeal.setStrMeasure17(jsonObject.get("strMeasure17").toString());

                if(jsonObject.get("strMeasure18") == null) apiMeal.setStrMeasure18("");
                else apiMeal.setStrMeasure18(jsonObject.get("strMeasure18").toString());

                if(jsonObject.get("strMeasure19") == null) apiMeal.setStrMeasure19("");
                else apiMeal.setStrMeasure19(jsonObject.get("strMeasure19").toString());

                if(jsonObject.get("strMeasure20") == null) apiMeal.setStrMeasure20("");
                else apiMeal.setStrMeasure20(jsonObject.get("strMeasure20").toString());


                if(jsonObject.get("strSource") == null) apiMeal.setStrSource("");
                else apiMeal.setStrSource(jsonObject.get("strSource").toString());

                if(jsonObject.get("strImageSource") == null) apiMeal.setStrImageSource("");
                else apiMeal.setStrImageSource(jsonObject.get("strImageSource").toString());

                if(jsonObject.get("strCreativeCommonsConfirmed") == null) apiMeal.setStrCreativeCommonsConfirmed("");
                else  apiMeal.setStrCreativeCommonsConfirmed(jsonObject.get("strCreativeCommonsConfirmed").toString());

                if(jsonObject.get("dateModified") == null) apiMeal.setDateModified("");
                else   apiMeal.setDateModified(jsonObject.get("dateModified").toString());

                apiMealList.add(apiMeal);

            }
            //Disconnect the HttpURLConnection stream
            conn.disconnect();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return apiMealList;
    }


    public List<ApiMeal> getMealsById(List<Integer> favorites) {

        List<ApiMeal> list =new LinkedList<>();
        for (Integer id : favorites)
              list.add(getMealById(id).get());
        return list;
    }
}
