/* EMI calculation*/
    function divideBy()
    {
    var num1 = document.getElementById("result").textContent;
    var num2 = document.getElementById("months").value;
    var num3 = num1/num2;
    var num4 =Math.round(num3);
    document.getElementById("emi").innerHTML = num4 ;
    //document.getElementById("tenurev").value = num2;
    $('#tenurev').val(num2);
    
     }
    

    
    
    