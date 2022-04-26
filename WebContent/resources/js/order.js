

$(document).ready(function()
{
    $(".rowToClick").click(function() { $(this).nextUntil(".rowToClick").toggle(); });
});
      