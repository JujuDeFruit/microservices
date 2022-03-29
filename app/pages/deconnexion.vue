<template>
    <v-container fluid class=" justify-center" fill-height>
      <v-card color="secondary" min-width="400">

        <v-card-title>
          Portail
        </v-card-title>

        <v-card-subtitle>
          Vous avez été déconnecté
        </v-card-subtitle>

        <v-divider />

        <v-card-actions>
          <v-btn color="primary" elevation="2" outlined rounded @click="$router.push('/connexion')">
            <v-icon left>
              mdi-fingerprint
            </v-icon>
            Connexion
          </v-btn>
        </v-card-actions>

      </v-card>
    </v-container>
</template>

<script>
export default {
  name: "Deconnexion",
  layout: 'empty',
}
</script>
