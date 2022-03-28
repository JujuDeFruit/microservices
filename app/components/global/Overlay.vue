<template>
  <v-overlay :value="overlay" color="accent" z-index="10000">
    <v-icon x-large color="warning">
      mdi-timer
    </v-icon>
  </v-overlay>
</template>

<script>
export default {
  name: "Overlay",
  model: {
    prop: 'overlay'
  },
  props: {
    overlay: {
      type: Boolean,
      default: false
    }
  }
}
</script>

<style scoped lang="scss">
.v-icon {
  -webkit-animation: linear infinite;
  -webkit-animation-name: run;
  -webkit-animation-duration: 2s;
}
@-webkit-keyframes run {
  0% {
    left: -150%;
  }
  50% {
    left: 150%;
  }
  100% {
    left: -150%;
  }
}
</style>
