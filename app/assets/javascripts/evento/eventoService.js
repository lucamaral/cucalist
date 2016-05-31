(function(id) {
  App.Evento.Service = {
    getEvento: function(id){
      console.info("cheguei no service getevento");
      console.info(id);
      var url= "/api/eventos/" + id ;
      return $.ajax({
        type: 'GET' ,
        url: url ,
        dataType: 'json'

      });
    }
  }
})()
