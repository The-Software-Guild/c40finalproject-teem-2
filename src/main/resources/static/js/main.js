function showResult(){
        var sb = document.querySelector('#categoryButton')
        var categoryPickedValue = sb.value
        var searchTerm = $('#searchTerm').val();
        $('#contentRows').empty();
         var contentRows = $('#contentRows');

        console.log(categoryPickedValue);
        if(categoryPickedValue == 's'){
            $.ajax({
                type: 'GET',
                url: 'https://www.themealdb.com/api/json/v1/1/search.php?s=' + searchTerm,
                success: function (meals) {
                  var index = 0;
                  var subObj = '';
                  var htm = '';
                  var dataMeals = meals['meals'];
                     for(index = 0; index < dataMeals.length; index++) {
                             meal    =   dataMeals[index];
                                     htm +='<tr>';
                                          htm+='<td><img src ="'+meal.strMealThumb+'" class="rounded" style="width:249px;height:249px;"/></td>';
                                          htm+='<td>'+meal.strMeal+'</td>';
                                          htm+='<td>'+meal.strMealThumb+'</td>';
                                          htm+='<td><a href="/favorite?id='+meal.idMeal+'">Favorite</a></td>';
                                     htm+='</tr>';

                                 }
                              // $("table").find("tr:gt(1)").remove();
                               contentRows.append(htm);


        }
                ,
                error: function(){
                    console.log('Error calling web service. Please try again later.');
                }
            });
        }
                if(categoryPickedValue == 'i'){
                    $.ajax({
                        type: 'GET',
                        url: 'https://www.themealdb.com/api/json/v1/1/filter.php?i=' + searchTerm,
                        success: function (meals) {
                          var index = 0;
                          var subObj = '';
                          var htm = '';
                          var dataMeals = meals['meals'];
                             for(index = 0; index < dataMeals.length; index++) {
                                     meal    =   dataMeals[index];
                                     console.log(subObj);
                                             htm +='<tr>';
                                                  htm+='<td><img src ="'+meal.strMealThumb+'" class="rounded" style="width:249px;height:249px;"/></td>';
                                                  htm+='<td>'+meal.strMeal+'</td>';
                                                  htm+='<td>'+meal.strMealThumb+'</td>';
                                                  htm+='<td><a href="/favorite?id='+meal.idMeal+'">Favorite</a></td>';
                                             htm+='</tr>';

                                         }
                                      // $("table").find("tr:gt(1)").remove();
                                       contentRows.append(htm);


                }
                        ,
                        error: function(){
                            console.log('Error calling web service. Please try again later.');
                        }
                    });
                }

                if(categoryPickedValue == 'c'){
                           $.ajax({
                                type: 'GET',
                                url: 'https://www.themealdb.com/api/json/v1/1/filter.php?c=' + searchTerm,
                                success: function (meals) {
                                  var index = 0;
                                  var subObj = '';
                                  var htm = '';
                                  var dataMeals = meals['meals'];
                                     for(index = 0; index < dataMeals.length; index++) {
                                             meal    =   dataMeals[index];
                                             console.log(subObj);
                                                     htm +='<tr>';
                                                          htm+='<td><img src ="'+meal.strMealThumb+'" class="rounded" style="width:249px;height:249px;"/></td>';
                                                          htm+='<td>'+meal.strMeal+'</td>';
                                                          htm+='<td>'+meal.strMealThumb+'</td>';
                                                          htm+='<td><a href="/favorite?id='+meal.idMeal+'">Favorite</a></td>';
                                                     htm+='</tr>';

                                                 }
                                              // $("table").find("tr:gt(1)").remove();
                                               contentRows.append(htm);


                        }
                                ,
                                error: function(){
                                    console.log('Error calling web service. Please try again later.');
                                }
                            });
                        }

                                if(categoryPickedValue == 'a'){
                                    $.ajax({
                                        type: 'GET',
                                        url: 'https://www.themealdb.com/api/json/v1/1/filter.php?a=' + searchTerm,
                                        success: function (meals) {
                                          var index = 0;
                                          var subObj = '';
                                          var htm = '';
                                          var dataMeals = meals['meals'];
                                             for(index = 0; index < dataMeals.length; index++) {
                                                     meal    =   dataMeals[index];
                                                     console.log(subObj);
                                                             htm +='<tr>';
                                                                  htm+='<td><img src ="'+meal.strMealThumb+'" class="rounded" style="width:249px;height:249px;"/></td>';
                                                                  htm+='<td>'+meal.strMeal+'</td>';
                                                                  htm+='<td>'+meal.strMealThumb+'</td>';
                                                                  htm+='<td><a href="/favorite?id='+meal.idMeal+'">Favorite</a></td>';
                                                             htm+='</tr>';

                                                         }
                                                      // $("table").find("tr:gt(1)").remove();
                                                       contentRows.append(htm);

                                }
                                        ,
                                        error: function(){
                                            console.log('Error calling web service. Please try again later.');
                                        }
                                    });
                                }

}
