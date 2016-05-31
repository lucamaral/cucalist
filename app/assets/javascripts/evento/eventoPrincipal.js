(function(id){
  App.Evento.Principal = {
    iniciar: function(id){
      console.info("cheguei no principal iniciar");
      App.Evento.Controller.iniciar(id);
    }
  }

})()
