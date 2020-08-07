$(document).ready(function() {

    // all global variables
    songids = [];
    songnames = [];
    let languages;
    var alblen = songnames.length;
    var idx = 0;
    var cursong = null;
    let current_song = document.getElementById("current-song");
    const play = document.querySelector('.play-pause');
    const seekBar = document.getElementById('seekbar');
    const searchBar = document.getElementById('search-bar');
    const search_results = document.querySelector('#search-res');
    let mute = document.getElementById('mute');
    let volume = document.getElementById('volume');
    let lang = document.getElementById('langs');
    const mobAlbum = document.querySelector(".mob-albums-list");
    const mobRecent = document.querySelector(".mob-recent");
    const closeAlbum = document.getElementById("albums-close");
    const closeRecent = document.getElementById("recent-close");
    const albumsdiv = document.getElementById("albums");
    const recentdiv = document.getElementById("recent");
    //initial
    const initialContent = document.querySelector(".initial-loading");
    const langContent = document.querySelector(".lang-select-loading");
    const card = document.getElementsByClassName("card-div");
    var temp;
    let muted = false;
    var allsongs;
    const albumCards = document.getElementsByClassName("album-cards");

    document.getElementById("backbutton").style.display = "none";
    seekBar.disabled = true;
    document.getElementById('current-song-album').style.display = "none";

    let changeContent = () => {
        if (lang.value === "") {
            document.getElementById("backbutton").style.display = "none";
            langContent.style.display = "none";
            initialContent.style.display = "block";
            albumsdiv.previousElementSibling.children[0].innerText = "Trending";
            mobAlbum.innerText = "Trending";
        } else {
            document.getElementById("backbutton").style.display = "block";
            langContent.style.display = "block";
            initialContent.style.display = "none";
            albumsdiv.previousElementSibling.children[0].innerText = "Albums";
            mobAlbum.innerText = "Albums";
        }
    }


    let backbutton = () => {
        lang.value = "";
        $('#albums').fadeOut(500);
        changeContent();
        $.ajax({
            url: 'TrendingSongs',
            success: function(responseText) {
                $('#albums').html(responseText);
                $('#albums').fadeIn(400);

            }
        });

    }

    let favs = (temp, mess) => {
        $.ajax({
            url: 'Favourites',
            data: {
                mes: mess,
                sid: $(temp).val(),
                uidd: uid
            },
            success: function(responseText) {
                if (mess == "get") {
                    $('#recent').html(responseText);
                    $('#recent').fadeIn(300);
                }
            }
        });

    }

    let renderRecents = () => {
        $.ajax({
            url: 'RecentlyPlayed',
            data: {
                uidd: uid
            },
            success: function(responseText) {
                $('#recent').html(responseText);
                $('#recent').fadeIn(300);
            }
        });
    }

    $(document).on('click', '#backbutton', backbutton);

    //$(document).ready(function() {
    ////////////////////////////////////////////////////////////////////// 
    renderRecents();

    $.ajax({
        url: 'Languages',
        data: {
            userName: ''
        },
        success: function(responseText) {

            $('#langs').html(responseText);
        }
    });



    $.ajax({
        url: 'TrendingSongs',
        success: function(responseText) {
            $('#albums').html(responseText);
        }
    });



    $.ajax({
        url: 'TrendingAlbums',
        success: function(responseText) {
            $('#trending_albumss').html(responseText);
        }
    });



    //});




    languages = (lang_clicked, source = "") => {

        $.ajax({
            url: 'Albums',
            data: {
                langclicked: lang_clicked
            },
            success: function(responseText) {
                $('#albums').html(responseText);
                if (source == "fromtop") {
                    document.getElementsByClassName('album-btn')[0].click();
                    $('#albums').fadeIn(400);
                }
            }
        });
    };


    $(document).on("change", "#langs", function() {
        $('#albums').fadeOut(700);
        languages(lang.value, source = "fromtop");

    });



    $(document).on("click", "#albumbutton", function(e) {

        var urlid = $(this).val();
        var button = e.target;
        temp = e.target;

        document.getElementById('current-song-album').innerHTML = button.name;
        document.getElementById('current-song-album').style.display = "block";

        var language = this.parentElement.parentElement.parentElement.classList[1];

        if (language === "search-section") {
            language = this.classList[1];
            search_results.innerHTML = "";
        }
        if (language.match(/L/)) {
            lang.value = language;
            languages(language);
        }

        var url = document.getElementById(urlid).value;

        document.getElementById('current-song-img').src = url;




        $.ajax({
            url: 'Songs',
            data: {
                album: $(this).val(),
                uidd: uid
            },
            success: function(responseText) {
                changeContent();
                $('#songs').html(responseText);
                language = "";

            }
        });

    });

    $(document).on("click", "#each_song", function() {
        $.ajax({
            url: 'RecentlyPlayed',
            data: {
                sid: $(this).find("button").attr('id'),
                uidd: uid
            },
            success: function(responseText) {
                $('#recent').html(responseText);

            }
        });

    });

    $(document).on('click', '#fav', function() {
        
        var icon = this.firstElementChild;
        if (icon.classList.contains("fa-heart")) {
            this.innerHTML = '<i class="fa fa-heart-o"  aria-hidden="true"></i>';
        } else {
            this.innerHTML = '<i class="fa fa-heart" style="color: #ff1e1e;" aria-hidden="true"></i>';
        }

        favs(this, "post");

    });

    $(document).on('click', '#fav-tab', function() {
        $('#recent-tab button').prop("disabled", false);
        $('#fav-tab button').prop("disabled", true);
        $('#recent').fadeOut(500);
        favs(this, "get");
        $('#fav-tab button').css("color", "dodgerblue");
        $('#recent-tab button').css("color", "white");
    });


    $(document).on('click', '#recent-tab', function() {
        $('#fav-tab button').prop("disabled", false);
        $('#recent-tab button').prop("disabled", true);
        $('#recent').fadeOut(400);
        renderRecents();
        $('#recent-tab button').css("color", "dodgerblue");
        $('#fav-tab button').css("color", "white");



    });


    //document.getElementById('songlist').children[1].getElementsByTagName('button')[0].id



    $('#search-bar').on('input', function() {
        $.ajax({
            url: 'SearchServlet',
            data: {
                searchvalue: $(this).val()
            },
            success: function(responseText) {

                $('#search-res').html(responseText);
            }
        });

    });




    // toggle play pause
    let playpause = () => {
        if (current_song.currentSrc !== "") {
            if (current_song.paused)
                current_song.play();
            else
                current_song.pause();
        }
    }

    //  when song is paused
    let songPaused = () => {
        play.innerHTML = '<i class="fa fa-play fa-lg" aria-hidden="true"></i>';
    }

    // when song is playing
    let songPlaying = () => {
        play.innerHTML = '<i class="fa fa-pause fa-lg" aria-hidden="true"></i>';
    }

    // update seek bar
    let updateSeekBar = () => {
        seekBar.value = current_song.currentTime;
    }

    // at the end of the song			
    let songEnd = () => {
        play.innerHTML = '<i class="fa fa-play fa-lg" aria-hidden="true"></i>';
        playNext();
    }

    // on keypress of spacebar playpause() is called
    window.addEventListener('keypress', key => {
        if (key.which === 32)
            playpause();
    })



    function playSong(id, name, classname = "") {

        //set if song is favourite
        /*var k = document.getElementById('fav');
        document.querySelector('.bottom-fav').innerHTML=k.innerHTML;
        document.querySelector('.bottom-fav').value=k.value;*/
        //loading animation starts
        play.classList.add("song-load")
        seekBar.disabled = false;

        document.getElementById('current-song-title').innerText = name;
        document.getElementById('current-song-title-mob').innerText = name;
        document.title = name;

        var songurl = document.getElementsByClassName(id)[0].value;

        document.getElementById('play-song').innerHTML = "<audio controls class='" + name + "' id='current-song'><source src=" + songurl + " type='audio/mpeg'></audio>";

        current_song = document.getElementById("current-song");
        if (muted) {
            mute.innerHTML = '<i style="color:red;" class="fa fa-volume-off fa-lg" aria-hidden="true"></i>';
            current_song.muted = true;
            current_song.volume = 0;
            updateVolume();
        }
        current_song.oncanplay = () => {
            let minutes = parseInt(current_song.duration / 60, 10);
            let seconds = parseInt(current_song.duration % 60);
            document.getElementById("play-end").innerText = minutes + ':' + seconds;
            if (current_song.paused)
                current_song.play();
            play.classList.remove("song-load");
            current_song.ontimeupdate = updatePlayTime;
            current_song.onended = songEnd;
            current_song.onpause = songPaused;
            current_song.onplay = songPlaying;
            seekBar.max = current_song.duration;
        }

        if (classname != "") {
            songids.length = 0;
            songnames.length = 0;
            allsongs = document.getElementsByClassName(classname);
            for (var i = 0; i < allsongs.length; i++) {
                songids.push(allsongs[i].id);
                songnames.push(allsongs[i].value);
            }
        }
    };

    // mute
    let muteUnmute = () => {
        if (current_song.muted) {
            muted = false;
            mute.innerHTML = '<i class="fa fa-volume-up fa-lg" aria-hidden="true"></i>';
            current_song.muted = false;
            current_song.volume = 1;
        } else {
            muted = true;
            mute.innerHTML = '<i style="color:red;" class="fa fa-volume-off fa-lg" aria-hidden="true"></i>';
            current_song.muted = true;
            current_song.volume = 0;
        }
        updateVolume();
    }

    // update volume 
    let updateVolume = () => {
        volume.value = current_song.volume;
    }

    // select language 
    /*	let selectLang = () => {
    	    if (lang.vale === "") {
    	        document.querySelector('.lang-select').classList.add("shake");
    	    } else {
    	        document.querySelector('.lang-select').classList.remove("shake");
    	    }
    	}
    */
    // play pause event
    play.addEventListener('click', playpause);


    // every time update on song
    let updatePlayTime = () => {
        let minutes = parseInt(current_song.currentTime / 60, 10);
        let seconds = parseInt(current_song.currentTime % 60);
        if (minutes < 10) {
            minutes = '0' + minutes.toString();
        }

        if (seconds < 10) {
            seconds = '0' + seconds.toString();
        }

        document.getElementById("play-current").innerText = minutes + ':' + seconds;
        updateSeekBar();
    }


    seekBar.addEventListener('input', () => {
        current_song.currentTime = parseInt(seekBar.value);
        playpause();
    });




    function playNext() {
        if (current_song.currentSrc != "") {
            cursong = document.getElementById("current-song").className;
            alblen = songnames.length;
            idx = songnames.indexOf(cursong);

            if (idx == alblen - 1) {
                idx = 0;
                playSong(songids[idx], songnames[idx], "");
            } else {
                playSong(songids[idx + 1], songnames[idx + 1], "");
            }
        }
    };

    function playPrev() {
        if (current_song.currentSrc != "") {
            cursong = document.getElementById("current-song").className;
            alblen = songnames.length;
            idx = songnames.indexOf(cursong);

            if (idx == 0) {
                idx = alblen - 1;
                playSong(songids[idx], songnames[idx])
            } else {
                playSong(songids[idx - 1], songnames[idx - 1]);
            }
        }
    }


    // remove album card on resizing in home page
    let album_card;
    let displayCards = () => {
        // let lenCards = albumCards.length;
        for (let i = 0; i < albumCards.length; i++) {
            album_card = albumCards[i].getElementsByClassName("album-card");
            if (window.innerWidth >= 1200) {
                [...album_card].forEach((item) => item.style.display = "inline-block");
            } else if (window.innerWidth >= 992 && window.innerWidth < 1200) {
                album_card[4].style.display = "none";
                album_card[3].style.display = "inline-block";
                album_card[2].style.display = "inline-block";
            } else if (window.innerWidth >= 768 && window.innerWidth < 992) {
                album_card[4].style.display = "none";
                album_card[3].style.display = "none";
                album_card[2].style.display = "inline-block";
            } else if (window.innerWidth < 768) {
                album_card[4].style.display = "none";
                album_card[3].style.display = "none";
                album_card[2].style.display = "none";
            }
        }
    }


    // search bar

    // on focus
    searchBar.addEventListener('focus', () => {
        search_results.style.display = "block";
    });
    // on blur
    searchBar.addEventListener('blur', () => {
        setTimeout(() => {
            search_results.style.display = "none";
            if (searchBar.value.length === 0)
                search_results.innerHTML = "No results";
        }, 500);
    });

    // On scroll search results should not be displayed

    window.onscroll = () => {
        search_results.style.display = "none";
        if (searchBar.value.length === 0)
            search_results.innerHTML = "No results";
        searchBar.blur();
    }


    // mute 

    mute.addEventListener('click', muteUnmute);

    // change volume

    volume.addEventListener('input', () => {
        current_song.volume = volume.value;
        updateVolume();
    });

    // if language is selected

    //	lang.addEventListener('mouseover', () => {
    //    document.querySelector('.lang-select').classList.remove("shake");
    //	});



    // on resizing window


    var resize_mob = () => {
        let firstRow = document.querySelector('.first-row');
        let recent = document.querySelector('.recent');
        if (window.innerWidth < 992) {
            firstRow.insertBefore(recent, firstRow.firstElementChild.nextSibling);
            document.querySelector('.albums-list').style.display = "none";
            document.querySelector('.recent').style.display = "none";
            mobAlbum.style.display = "block";
            mobRecent.style.display = "block";

        } else {
            recent.remove();
            firstRow.appendChild(recent);
            document.querySelector('.albums-list').style.display = "block";
            document.querySelector('.recent').style.display = "block";
            mobAlbum.style.display = "none";
            mobRecent.style.display = "none";
        }
    };

    window.addEventListener('resize', resize_mob);

    // on click of albums btn

    // gloabal objects added above for this 

    mobAlbum.addEventListener('click', () => {
        mobAlbum.style.display = "none";
        mobRecent.style.display = "none";
        document.querySelector('.albums-list').style.display = "block";

    })

    closeAlbum.addEventListener('click', () => {
        mobAlbum.style.display = "block";
        mobRecent.style.display = "block";
        document.querySelector('.albums-list').style.display = "none";
    })
    mobRecent.addEventListener('click', () => {
        mobAlbum.style.display = "none";
        mobRecent.style.display = "none";
        document.querySelector('.recent').style.display = "block";

    })

    closeRecent.addEventListener('click', () => {
        mobAlbum.style.display = "block";
        mobRecent.style.display = "block";
        document.querySelector('.recent').style.display = "none";
    })


    // added after sending file on 26th july added 2 variables above 
    // albumdiv and recentdiv

    albumsdiv.addEventListener('click', (event) => {
        const isbutton = event.target.nodeName === "BUTTON";
        if (isbutton && window.innerWidth < 992) {
            mobAlbum.style.display = "block";
            mobRecent.style.display = "block";
            document.querySelector('.albums-list').style.display = "none";
        }
    });

    recentdiv.addEventListener('click', (event) => {
        const isbutton = event.target.nodeName === "BUTTON";
        if (isbutton && window.innerWidth < 992) {
            mobAlbum.style.display = "block";
            mobRecent.style.display = "block";
            document.querySelector('.recent').style.display = "none";
        }
    })

    lang.addEventListener('change', changeContent);
    [...card].forEach((item, index) => {
        item.addEventListener('click', () => {
            const language = card[index].parentElement.parentElement.classList[1];
            lang.value = language;
            changeContent();
            languages(lang.value);
        })

    });

    resize_mob();
    displayCards();
})