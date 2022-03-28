<template>
  <v-app app>

    <v-app-bar
      color="secondary"
      fixed
      app>

      <v-spacer />

      <span style="font-size: 2em"><strong>{{ name }}</strong></span>

      <v-spacer />

      <v-tooltip left>
        <template #activator="{ attrs, on }">
          <v-btn fab color="error" small v-bind="attrs" v-on="on" @click="deconnexion">
            <v-icon>mdi-fingerprint-off</v-icon>
          </v-btn>
        </template>
        <span>Se déconnecter</span>
      </v-tooltip>


    </v-app-bar>

    <v-main>
      <v-container fluid class="pa-0">
        <overlay v-model="overlay"/>
        <Nuxt />
      </v-container>
    </v-main>

    <v-footer
      fixed
      app>
      <span>{{ name }} &copy; {{ new Date().getFullYear() }}</span>
    </v-footer>

  </v-app>
</template>

<script>
import Overlay from "@/components/global/Overlay";

export default {
  components: {
    Overlay,
  },
  data () {
    return {
      name: "Microservices",
      overlay: false,
    }
  },
  created() {
    this.$nuxt.$on('overlay', (val) => {
      this.overlay = val
    })
  },
  methods: {
    deconnexion() {
      this.$store.commit("session/resetUser")
      this.$router.push('/deconnexion')
    }
  }
}
</script>

<style lang="scss">
html {
  overflow: hidden;
}
.v-app-bar {
  z-index: 1000;
}
</style>
