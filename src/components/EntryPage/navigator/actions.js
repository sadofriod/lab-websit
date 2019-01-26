const GET_GEAR_ICON_URL = 'GET_GEAR_ICON_URL';
let getIconUrl = url => (
    {
        type: GET_GEAR_ICON_URL,
        url: url
    }
)
export { getIconUrl, GET_GEAR_ICON_URL }