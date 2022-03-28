export default function ({ store, redirect }) {
  // If the user is not authenticated
  if (!store.getters['session/isAuth']) {
    return redirect('/connexion')
  }
}
