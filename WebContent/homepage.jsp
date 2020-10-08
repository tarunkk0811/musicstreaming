<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="ISO-8859-1">
    <!-- responsive for devices-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- URL COLOR -->
    <meta name="theme-color" content="#343a40">

    <!-- Google fonts -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Karla|Archivo|Roboto">

    <!--jQuery-->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>

    <!-- Bootstarp -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous" />
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>

    <!-- Font Awesome -->
    <script src="https://use.fontawesome.com/bae75bb48f.js"></script>

    <!-- ajax -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <!--css files -->
    <link rel="icon" href="css/music.png" type="image/icon type">
    <link rel="stylesheet" href="home-styles.css">
    <link rel="shortcut icon" href="#">

    <title>Music Streamer</title>
</head>

<body>
<!-- Valid Session Check -->
<%
response.setHeader("cache-control","no-cache, no-store, must-revalidate");//http 1.1
response.setHeader("Pragma","no-cache");//http 1.0
response.setHeader("Expires","0");//Proxies

if(session.getAttribute("uid")==null){
	request.setAttribute("message","session invalid");
	response.sendRedirect("login.jsp");		
}
%>

<!-- current user id -->
<% String uid= (String) session.getAttribute("uid"); %>
    <!-- Valid Session Check -->
    <script>
        const uid = '${uid}';
    </script>
    
    <!-- nav start -->
    <nav class="navbar navbar-dark bg-dark navbar-expand-md">
        <a id='titlename' href="#" class="navbar-brand">Music Streamer</a>
        <!-- Toggler -->
        <button class="navbar-toggler" data-toggle="collapse" data-target="#myNav">
        <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="myNav">
            <div class="mx-auto search-section">
                <input id="search-bar" autocomplete="off" class="form-control mr-sm-2 search-bar" type="search" style="color: rgb(101, 101, 255);" placeholder="Search" aria-label="Search">
                <div id="search-res">
                </div>
            </div>
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <i class="fa fa-cog" aria-hidden="true"></i>
                    <select id='langs' class="select-language">
                        <option value="" disabled selected>
                           Language
                        </option>
           		    </select>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					${name}
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="#">${name} </a>
                        <form action="Logout" method="post"><input class="dropdown-item" type="submit" value="Logout" /></form>
                        <!-- To check whether session is valid and role is admin. if role is admin we will give access to admin page -->
						     <% if(session.getAttribute("role")==null); 
                  else if(session.getAttribute("role").equals("admin")){%>
                        <a class="dropdown-item" href="admin.jsp"><strong>Admin</strong></a>
						   <%}%>
                    </div>
                </li>
            </ul>
        </div>
    </nav>
    <!-- Nav end -->
    
    <div class="first-row row mx-auto">
    
        <div class="sideBars albums-list col-lg-2 col-12">
            <p> <span id='trend'>Trending</span>
                <span id="albums-close"><button><i class="fa fa-times" style="color: white;" aria-hidden="true"></i></button></span>
            </p>
            <div id="albums" style="border-bottom:0px;">
            </div>
        </div>
        
        
        <div class="main col-xs-12 col-lg-8 mx-auto">
            <div class="back-button">
                <button id="backbutton">
                <i class="fa fa-chevron-left fa-lg" aria-hidden="true"></i> 
                <span class="col-lg-12 text-light font-weight-bold text-capitalize" id="current-song-album" ></span>
                </button>
            </div>
            <button class="mob-albums-list">Trending</button>
            <div class="lang-select-loading">
                <div class="album-name-img">
                    <div class="col-lg-12 current-song-img">
                        <img src="https://thumbs.gfycat.com/ChubbyIlliterateCanadagoose-small.gif" id="current-song-img" style="max-width: 250px; border-radius: 30px; max-height: 250px;" alt="Loading..">
                    </div>
                </div>
                <ul id="songs" class="album-songs">
                </ul>
            </div>
            <div class="initial-loading" id="trending_albumss">
            </div>
            <button class="mob-recent">Recent</button>
        </div>
        
        
        
        <div class="sideBars recent col-lg-2">
            <p class="recent-fav">
                <span id="recent-tab"><button disabled>Recent</button></span>
                <span id="fav-tab"><button>Favourites</button></span>
                <span id="recent-close"><button><i class="fa fa-times" style="color: white;" aria-hidden="true"></i></button></span>
            </p>
            <div id="recent" style="border-bottom:0px;">
            </div>
        </div>
    </div>

    <div id="player-pannel-container">
        <div class="player-pannel">
            <div class="current-song-details mx-left">
                <img src="https://cdn.dribbble.com/users/1526471/screenshots/3709702/equalizer.gif" id="current-song-img" style="max-width: 80px; height: 80px;" alt="Loading..">
                <span id="current-song-title"></span>
                <!-- <span class="song-fav-icon"><button class="bottom-fav" value='' id='fav'><i class="fa fa-heart-o" aria-hidden="true"></i></button></span> -->
            </div>
            <center>
                <div class="control-buttons mx-auto centered">
                    <button class="playPrev" onclick="playPrev()"><i class="fa fa-step-backward fa-lg" aria-hidden="true"></i></button>

                    <button class="play-pause"><i class="fa fa-play fa-lg" aria-hidden="true"></i></button>

                    <button class="playNext" onclick="playNext()"><i class="fa fa-step-forward fa-lg" aria-hidden="true"></i></button>

                    <div id="play-song" hidden=""><audio controls="" class="Oola olala" id="current-song"><source src="" type="audio/mpeg"></audio></div>
                </div>
                <div class="progress-time mx-auto centered">
                    <span id="play-current">00:00</span>
                    <span id="play-length">
                           <input type="range" name="seekbar" id="seekbar" min="0" step="0.25" value="0" max="336.738702">
                       </span>
                    <span id="play-end">00:00</span>
                </div>
                <div id="current-song-title-mob" class="mob d-lg-none">
                </div>
            </center>
            <div class="other-controls mx-right d-flex align-items-center">
                <button id="mute"><i class="fa fa-volume-up fa-lg" aria-hidden="true"></i></button>
                <input type="range" name="volume" id="volume" min="0" step="0.2" max="1" value="1">
            </div>
        </div>
    </div>
    <script src="js/app.js"></script>
</body>
</html>