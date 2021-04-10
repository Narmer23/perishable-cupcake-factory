import moment from "moment";
import Date from "src/filters/Date";
import Vuelidate from "vuelidate";
export default ({ Vue }) => {
  Vue.use(Vuelidate);
  Vue.filters = { Date };
  moment.locale("it");
};
