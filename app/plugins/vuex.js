import VuexPersistence from 'vuex-persist'

export default ({ store }) => {
  new VuexPersistence({
    key: 'session',
    storage: window.localStorage, // or window.sessionStorage or localForage
  }).plugin(store)
}
