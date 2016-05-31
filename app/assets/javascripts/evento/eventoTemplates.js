(function() {

  App.Evento.Templates = {
    _init: function(){
      console.info("cheguei no templates _init");
          App.Evento.Templates.EventoTemplate = $('#evento-dados-template').html();
          Mustache.parse(App.Evento.Templates.EventoTemplate);
    }
  };

  App.Evento.Templates._init();
})();
