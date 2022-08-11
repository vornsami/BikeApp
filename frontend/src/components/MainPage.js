import React, { useEffect, useState }  from 'react'

import Box from '@material-ui/core/Box'
import { useParams } from 'react-router-dom'

import getPaths from '../services/network'
import PathList from './PathList'
import FailPage from './FailPage'

const MainPage = () => {
  let props = useParams();
  const page = (props.page)? props.page : 1
  const pageLength = 25 

  const [paths, setPaths] = useState([])
  const [sort, setSort] = useState('Distance')

  useEffect(() => {
    async function fetchData() {
      const offset = page * pageLength - pageLength
      const initialPaths = await getPaths(sort, pageLength, offset)
      setPaths(initialPaths)
    }
    try {
      fetchData()
    } catch (e) {
      console.error(e)
    }
  }, [sort])

  return <Box>
      {paths !== undefined && <PathList paths={paths} setSort={setSort} sortBy={sort} page={page}/>}
    </Box>
}

export default MainPage