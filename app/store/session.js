import {MICRO_AUTH} from "@/constant/api";

export const state = () => ({
  user: null
})

export const getters = {
  isAuth: state => state.user !== null,
  getId: state => state.user.id,
  getUser: state => state.user
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
  async inscription({ state, commit }, user) {
    try {
      const res = await this.$axios.post(MICRO_AUTH.concat('/authentication/register'), {...user})
      if (res.status === 200) {
        commit('connexion', user)
        await this.$router.push('/')
      }
    } catch (e) {
      if (e.message.includes('409')) {
        throw new Error("L'email est déjà associé à un compte")
      } else throw new Error("Erreur inconnue")
    }
  }
}
