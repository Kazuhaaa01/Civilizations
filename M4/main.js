document.addEventListener("DOMContentLoaded", () => {
  // 1. Navegacion SPA
  const navLinks = document.querySelectorAll("#nav-menu a");
  const sections = document.querySelectorAll(".content-section");

  navLinks.forEach((link) => {
    link.addEventListener("click", (e) => {
      e.preventDefault();

      // Actualizar enlaces activos
      navLinks.forEach((l) => l.classList.remove("active"));
      link.classList.add("active");

      // Ocultar secciones y mostrar seleccionada
      const targetId = link.getAttribute("data-target");
      sections.forEach((section) => {
        section.classList.remove("active");
      });
      document.getElementById(targetId).classList.add("active");

      // Si entramos a civilizacion, cargar datos si no estan cargados
      if (targetId === "civilizacion") {
        loadCivilizationData();
      }
    });
  });

  // 2. Fetch API para cargar datos JSON
  let isDataLoaded = false;

  async function loadCivilizationData() {
    if (isDataLoaded) return; // Evitar múltiples peticiones

    const container = document.getElementById("resources-container");

    try {
      // Simula llamada a backend Java
      const response = await fetch("datos_civilizacion.json");

      if (!response.ok) {
        throw new Error("Error al conectar con la base de datos");
      }

      const data = await response.json();

      // Limpiar mensaje de carga
      container.innerHTML = "";

      // Generar tarjetas de recursos dinámicamente
      const resources = [
        { name: "Comida", amount: data.food, icon: "🌾" },
        { name: "Madera", amount: data.wood, icon: "🪵" },
        { name: "Hierro", amount: data.iron, icon: "⛏️" },
        { name: "Maná", amount: data.mana, icon: "✨" },
      ];

      resources.forEach((res) => {
        const card = document.createElement("div");
        card.classList.add("resource-card");
        card.innerHTML = `
                    <h3>${res.icon} ${res.name}</h3>
                    <div class="amount">${res.amount.toLocaleString()}</div>
                `;
        container.appendChild(card);
      });

      isDataLoaded = true;
    } catch (error) {
      console.error("Error fetching data:", error);
      container.innerHTML = `<p style="color: red;">Error: No se pudieron cargar los datos del servidor de Proxmox.</p>`;
    }
  }
});
