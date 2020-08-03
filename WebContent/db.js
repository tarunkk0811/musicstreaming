//  web app's Firebase configuration
const firebaseConfig = {
  apiKey: "AIzaSyDgV2iFniMS_x1A7Vczs-cpPAL4V73erhs",
  authDomain: "fir-8ed58.firebaseapp.com",
  databaseURL: "https://fir-8ed58.firebaseio.com",
  projectId: "fir-8ed58",
  storageBucket: "fir-8ed58.appspot.com",
  messagingSenderId: "577484619041",
  appId: "1:577484619041:web:e6020550efcfea2bcb9260",
  measurementId: "G-RMVX82TEWD"
};
// Initialize Firebase
firebase.initializeApp(firebaseConfig);

var files = [];
document.getElementById("files").addEventListener("change", function(e) {
  files = e.target.files;
  for (let i = 0; i < files.length; i++) {
    console.log(files[i]);
  }
});
document.getElementById("send").addEventListener("click", function() {
  //checks if files are selected
  if (files.length != 0) {
    //Loops through all the selected files
    for(let i = 0; i < files.length; i++) {
      //create a storage reference
      var path=document.getElementById("lang").value.toUpperCase()+'/'+document.getElementById("album").value.toUpperCase()+'/'+document.getElementById("sname").value.toUpperCase();
	  document.getElementById("sp").innerHTML=path;
	  var storage = firebase.storage().ref(path);

      //upload file
      var upload = storage.put(files[i]);
		
      //update progress bar
      upload.on(
        "state_changed",
        function progress(snapshot) {
          var percentage =
            (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
          document.getElementById("progress").value = percentage;
        },

        function error() {
          alert("error uploading file");
        },
		
        function complete() {
		  getFileUrl(path,"url");
          document.getElementById("uploading").innerHTML += `${files[i].name} uploaded 	<br />`;
        }	
      );
    }
  } else {
    alert("No file chosen");
  }
});

//////////////////////POSTER ///////////////////////////


posterfiles=[];

document.getElementById("posterfiles").addEventListener("change", function(e) {
  posterfiles = e.target.files;
  for (let i = 0; i < posterfiles.length; i++) {
    console.log(posterfiles[i]);
  }
});
document.getElementById("sendposter").addEventListener("click", function() {
  //checks if files are selected
  if (posterfiles.length != 0) {
    //Loops through all the selected files
    for(let i = 0; i < posterfiles.length; i++) {
      //create a storage reference
      var path = document.getElementById("lang").value.toUpperCase()+'/'+document.getElementById("album").value.toUpperCase()+'/'+"POSTER";
	  document.getElementById("pp").innerHTML=path;
	  var storage = firebase.storage().ref(path);
      //upload file
      var upload = storage.put(posterfiles[i]);
      //update progress bar
      upload.on(
      "state_changed",
      function progress(snapshot) {
      var percentage =
            (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
          document.getElementById("posterprogress").value = percentage;
        },

        function error() {
          alert("error uploading file");
        },
		
        function complete() {
		  getFileUrl(path,"posterurl");
          document.getElementById("uploadingposter").innerHTML += `${posterfiles[i].name} uploaded <br />`;
        }	
      );
    }
  } else {
    alert("No file chosen");
  }
});






function getFileUrl(path,id) {
  var storage = firebase.storage().ref(path);
  storage
    .getDownloadURL()
    .then(function(url) {
	document.getElementById(id).value+=url;
      console.log(url);  
    }).catch(function(error) {
      console.log("error encountered");
    });
}

