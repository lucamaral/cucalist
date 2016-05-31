(function() {

  App.Evento.View = {

  renderizarItem: function(evento){
    console.info("cheguei no view renderizar item");
    var rendered = Mustache.render(App.Evento.Templates.EventoTemplate, evento)
    $('#evento').append(rendered);
  }
}
})()
