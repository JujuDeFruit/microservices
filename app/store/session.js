const MICRO_AUTH = 'http://localhost:8080/api'

export const state = () => ({
  user: null
})

export const getters = {
  isAuth: state => state.user !== null
}

export const mutations = {
  connexion(state, user) {
    state.user = user
  },
  resetUser(state) {
    state.user = null
  }
}

export const actions = {
  async connexion({ state, commit }, credentials) {
    const res = await this.$axios.get(MICRO_AUTH.concat('/authentication/login'), {
      params: credentials
    })
    if (res.status === 200) {
      commit('connexion', res.data)
    } else if(res.status === 204) throw new Error("Le mot de passe et l'email ne correspondent pas")
    else throw new Error("Erreur inconnue")
  },
}
