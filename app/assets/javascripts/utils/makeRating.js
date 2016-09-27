App.MakeRating = {
  makeRating: function(element, callback, input, magic) {
    var cucas = input.cucas;
    _.each(element, function(num, key, list){
        var myRating = rating(num, 0, 5, function(rating) {
          var id = $(num).closest('li').data("id");
          var ratingObj = {
            'id': id ,
            'rating': rating
          };
          callback(ratingObj);
        });
        var id = $(num).closest('li').data("id");
        _.each(cucas, function(cuca, i) {
          if(cucas[i].id == id) {
            if(magic == 1){
                myRating.setRating(cucas[i].nota);
            }else if(magic == 2){
                myRating.setRating(cucas[i].notaMedia);
            }
          }
        })
    });

  }
}
