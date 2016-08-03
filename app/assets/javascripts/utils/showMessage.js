// (function() {
  App.showMessage = {

  message: function($component, msg, template){
    $component.empty();
    $component.append(Mustache.render(template, {
        "message": msg
    }));
    $component.fadeIn("slow");

    setTimeout(function() {
        $component.fadeOut("slow");
    }, 3000);

    $component.click(function() {
        $component.fadeOut("slow");
    });
  },

  error: function($component, msg) {
          App.showMessage.message($component, msg, App.Templates.error);
  }

}
// });
