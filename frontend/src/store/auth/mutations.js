export function changeUser ( state, user) {
  state.user = user;
  state.isAuthenticated = !!user;
}
