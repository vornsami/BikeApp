import axios from 'axios'
import { API_URL } from '../../config'

const getPaths = async (sortBy, num, offset) => {
  const data = { sortBy: sortBy, amount: num, offset: offset }
  const response = await axios.post(API_URL, data)
  return response.data
}

export default getPaths