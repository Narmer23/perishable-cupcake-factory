
const routes = [
  {
    path: "/admin",
    component: () => import("layouts/MainLayout.vue"),
    children: [
      { path: "", component: () => import("pages/Batches.vue") },
      { path: "cakes", component: () => import("pages/Cakes.vue") },
      { path: "uoms", component: () => import("pages/Uoms.vue") },
      { path: "ingredients", component: () => import("pages/Ingredients.vue") }
    ]
  },
  {
    path: "/",
    meta:{public: true},
    component: () => import("pages/Showcase.vue")
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: "*",
    component: () => import("pages/Error404.vue")
  }
];

export default routes;
