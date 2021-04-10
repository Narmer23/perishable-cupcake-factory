import Vue from "vue";
import moment from "moment";

export default Vue.filter("date", formatDate);

export function formatDate(val, timeString = "L LTS") {
  if(!val)
    return "-";
  let formattedDate;
  if(isNaN(parseInt(val))){
    formattedDate = moment(val);
  } else{
    formattedDate = moment(parseInt(val));
  }
  return formattedDate.isValid() ? (timeString=="calendar" ? formattedDate.fromNow() : formattedDate.format(timeString)) : "-";
}