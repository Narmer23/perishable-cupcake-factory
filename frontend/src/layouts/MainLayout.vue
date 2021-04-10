<template>
  <q-layout view="lHh Lpr lFf">
    <q-header elevated>
      <q-toolbar>
        <q-btn
          flat
          dense
          round
          icon="menu"
          aria-label="Menu"
          @click="leftDrawerOpen = !leftDrawerOpen"
        />
        <img
          alt="Logo"
          src="pcf_long_white.svg"
        >
        <q-toolbar-title>
        </q-toolbar-title>

        <q-btn-dropdown dense icon="account_circle" :label="`${user.firstName} ${user.lastName}`" no-caps flat>
          <q-list>
            <q-item v-close-popup clickable @click="logout">
              <q-item-section avatar>
                <q-icon name="logout" color="secondary" />
              </q-item-section>
              <q-item-section>
                <q-item-label>Logout</q-item-label>
              </q-item-section>
            </q-item>
          </q-list>
        </q-btn-dropdown>
      </q-toolbar>
    </q-header>

    <q-drawer
      v-model="leftDrawerOpen"
      show-if-above
      bordered
      content-class="bg-grey-1"
    >
      <q-list>
        <q-item-label header class="text-grey-8">
          Menu
        </q-item-label>
        <q-item v-for="(link, i) in linksData" :key="i" clickable :to="link.to" exact>
          <q-item-section v-if="link.icon" avatar>
            <q-icon :name="link.icon" />
          </q-item-section>
          <q-item-section>
            <q-item-label>{{ link.title }}</q-item-label>
          </q-item-section>
        </q-item>
        <q-separator />
        <q-item clickable to="/" exact>
          <q-item-section avatar>
            <q-icon name="dashboard" color="secondary" />
          </q-item-section>
          <q-item-section>
            <q-item-label>Vetrina</q-item-label>
          </q-item-section>
          <q-item-section avatar>
            <q-icon name="open_in_new" color="grey" />
          </q-item-section>
        </q-item>
      </q-list>
    </q-drawer>

    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script>

const linksData = [
  {
    title: "Produzione cupcake",
    icon: "trending_up",
    to: "/admin"
  },
  {
    title: "Cupcake",
    icon: "cake",
    to: "/admin/cakes"
  },
  {
    title: "Ingredienti",
    icon: "list_alt",
    to: "/admin/ingredients"
  },
  {
    title: "Unità di misura",
    icon: "square_foot",
    to: "/admin/uoms"
  }
];

import {mapState} from "vuex";

export default {
  name: "MainLayout",
  data () {
    return {
      leftDrawerOpen: false,
      linksData
    };
  },
  computed: mapState({
    user: state => state.auth.user
  }),
  methods: {
    logout(){
      this.$store.commit("auth/changeUser", null);
      this.$router.replace("/");
    }
  }
};
</script>
