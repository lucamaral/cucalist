(function(){
  App.Eventos.View = {

    limparListaEventos: function(){
      $('#eventos-list').empty();
    },
    renderizarItems: function(eventosInput) {
      console.info("cheguei no renderizarItems");
      console.info(eventosInput);
        var rendered = Mustache.render(App.Eventos.Templates.ListItemTemplate, eventosInput);
        $('#eventos-list').append(rendered);
    },
    bindRemoverEvento: function(removeCallback){
      $('.eventos-button-remover').off();
      $('.eventos-button-remover').click(function(event) {
          var id = $(event.target).closest('li').data("id");
          removeCallback(id);
      });
    }

  }
})()
