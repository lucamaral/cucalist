(function() {

  App.Evento.View = {

  renderizarItem: function(evento){
    var rendered = Mustache.render(App.Evento.Templates.EventoTemplate, evento)
    $('#evento').append(rendered);
  }
}
})()
