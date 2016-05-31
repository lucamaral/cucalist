(function(){

  App.Eventos.Service = {
    list: function(searchTerm){
      console.info("cheguei no service list");
      var url= '/api/eventos'
      if(searchTerm != null){
        url += "?searchTerm=" + searchTerm;
      }
      return $.ajax({
          type: 'GET',
          url: url,
          dataType: 'json'
      });
    }

  }

})()
