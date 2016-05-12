(function() {
    App.Cucas.Controller = {
        iniciar: function() {
            App.Cucas.Controller.renderizar();
            App.Cucas.View.bindNovaCuca(App.Cucas.Controller.novaCuca);
            App.Cucas.View.bindPesquisaCuca(App.Cucas.Controller.pesquisar);
        },
        renderizar: function(searchTerm) {
            var cucasPromise = App.Cucas.Service.list(searchTerm);
            cucasPromise.then(App.Cucas.Controller.renderizarCucas);
        },
        renderizarCucas: function(cucas) {
            App.Cucas.View.limparLista();
            var cucasInput = {
              "cucas": cucas
            };
            App.Cucas.View.renderizarItems(cucasInput);
        },
        novaCuca: function(novaCuca) {
            var novaCucaPromise = App.Cucas.Service.nova(novaCuca);
            novaCucaPromise.then(function () {
                App.Cucas.Controller.renderizar();
            });
        },
        pesquisar: function(searchTerm){
            console.info(searchTerm);
            App.Cucas.Controller.renderizar(searchTerm);
        }
    };

})();
