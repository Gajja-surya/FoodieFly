document.addEventListener("DOMContentLoaded", function () {
    const toggleButton = document.getElementById("dark-mode-toggle");
    const body = document.body;

    // Check if dark mode was previously enabled
    if (localStorage.getItem("darkMode") === "enabled") {
        body.classList.add("dark-mode");
        updateButton('dark');
    } else {
        updateButton('light');
    }

    toggleButton.addEventListener("click", function () {
        body.classList.toggle("dark-mode");

        // Save the mode in localStorage and update button
        if (body.classList.contains("dark-mode")) {
            localStorage.setItem("darkMode", "enabled");
            updateButton('dark');
        } else {
            localStorage.removeItem("darkMode");
            updateButton('light');
        }
    });

    function updateButton(mode) {
        if (mode === 'dark') {
            toggleButton.innerHTML = '<i class="fas fa-sun"></i> Light Mode';
        } else {
            toggleButton.innerHTML = '<i class="fas fa-moon"></i> Dark Mode';
        }
    }
});