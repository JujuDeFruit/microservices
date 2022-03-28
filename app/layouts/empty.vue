<template>
  <v-app fixed app>

    <v-main ref="main">
      <v-container fluid class="pa-0">
        <overlay v-model="overlay" />
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
  name: "Empty",
  components: {
    Overlay
  },
  data() {
    return {
      overlay: false,
      name: 'Microservices'
    }
  },
  created() {
    this.$nuxt.$on('overlay', (val) => {
      this.overlay = val
    })
  },
}
</script>
