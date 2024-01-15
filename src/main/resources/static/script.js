function toggleLogs() {
    var logsContainer = document.getElementById("logs-container");
    if (logsContainer.style.display === "none") {
        logsContainer.style.display = "block";
    }
    else {
        logsContainer.style.display = "none";
    }
}

function toggleSettings() {
    var settingsContainer = document.getElementById("settings-form");
    if (settingsContainer.style.display === "none") {
        settingsContainer.style.display = "block";
    }
    else {
        settingsContainer.style.display = "none";
    }
}