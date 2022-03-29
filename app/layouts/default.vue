<template>
  <v-app app>

    <v-navigation-drawer
      color="#d3d3d3"
      :mini-variant='miniVariant'
      :items='items'
      class="py-5"
      app
      fixed
      width="300"
    >

      <v-list>
        <v-list-item
          v-for="(item, i) in items"
          :key="i"
          :to="item.to"
          router
          exact
        >
          <v-tooltip bottom>
            <template #activator="{ on, attrs }">
              <v-list-item-action v-bind="attrs" v-on="on">
                <v-icon>{{ item.icon }}</v-icon>
              </v-list-item-action>
              <v-list-item-content v-bind="attrs" v-on="on">
                <v-list-item-title v-text="item.title" />
              </v-list-item-content>
            </template>
            <span>{{ item.title }}</span>
          </v-tooltip>
        </v-list-item>
      </v-list>

    </v-navigation-drawer>

    <v-app-bar
      color="secondary"
      fixed
      app>

      <v-btn
        icon
        @click.stop="miniVariant = !miniVariant"
      >
        <v-icon>mdi-{{ `chevron-${miniVariant ? 'right' : 'left'}` }}</v-icon>
      </v-btn>

      <v-spacer />

      <span style="font-size: 2em"><strong>Mon CV</strong></span>

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
      miniVariant: false,
      items: [
        {
          icon: 'mdi-account',
          title: 'Données personnelles',
          to: '/'
        },
        {
          icon: 'mdi-briefcase',
          title: 'Expériences professionnelles',
          to: '/experiences'
        },
        {
          icon: 'mdi-school',
          title: 'Formation scolaire',
          to: '/formation'
        },
        {
          icon: 'mdi-check-decagram-outline',
          title: 'Compétences',
          to: '/competences'
        },
      ]
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
