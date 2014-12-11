<%-- 
    Document   : index
    Created on : 
    Author     : Jonathan Anastasiou
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link href='http://fonts.googleapis.com/css?family=Lato:400,700' rel='stylesheet' type='text/css'>
        <link rel = "stylesheet" type = "text/css" href = "geus.css">
        <style type="text/css">
            body{
                font-family: 'Lato', sans-serif;
            }

            #header{               
                background-image: url(images/fading_background_s.png);
                background-repeat: repeat-x;
                border-bottom: 2px solid #E6E6E6;
                width: 100%;
                overflow: hidden;
            }

            .imgPosition{
                padding-top: 65px;
            }

            .uploadButton{
                padding-left: 270px;
            }
            
            .links{
                text-decoration: none !important;
                font-size: 14pt;
                color: black;
            }
            
            .links:hover{
                color: lightblue;
            }
        </style>
        <title>Insert title here</title>
    </head>
    <body>
        <div id="header">
            <img src="http://www.geus.dk/repository/icons/logo-30.gif" align="left" height="100px">
            <img src="http://www.geus.dk/repository/icons/dggu-dk.gif" align="left" class="imgPosition">
        </div>
        <br />
        <a href="savePejling.jsp" class="links">Intast ny pejling</a><br>
        <a href="pejleSerie.jsp" class="links">Indlæs pejleserie</a><br>
    </body>
</html>