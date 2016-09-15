App.Rating = {
  applyRating: function(element, callback){
    var el = document.querySelector(element);
    return rating(el, 0, 5, callback);
  }
}
