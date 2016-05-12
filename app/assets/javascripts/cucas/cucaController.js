(function() {
    App.Cucas.Controller = {
        iniciar: function() {
            App.Cucas.Controller.renderizar();
            App.Cucas.View.bindNovaCuca(App.Cucas.Controller.novaCuca);
        },
        renderizar: function() {
            var cucasPromise = App.Cucas.Service.list();
            cucasPromise.then(App.Cucas.Controller.renderizarCucas);
        },
        renderizarCucas: function(cucas) {
            App.Cucas.View.limparLista();
            for (var i = 0; i < cucas.length; i++) {
                var cuca = cucas[i];
                App.Cucas.View.renderizarItem(cuca);
            }
        },
        novaCuca: function(novaCuca) {
            var novaCucaPromise = App.Cucas.Service.nova(novaCuca);
            novaCucaPromise.then(App.Cucas.Controller.renderizar);
        }
    };

})();
